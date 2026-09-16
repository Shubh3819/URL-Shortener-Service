package com.example.urlshortener.service;

import com.example.urlshortener.dto.CreateUrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.exception.UrlNotFoundException;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.util.Base62Encoder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final StringRedisTemplate redisTemplate;

    public UrlService(UrlRepository urlRepository, StringRedisTemplate redisTemplate) {
        this.urlRepository = urlRepository;
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public UrlResponse createShortUrl(CreateUrlRequest request) {
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(request.url());

        // Save first so the generated database ID can be converted to Base62.
        mapping.setShortCode("TEMP");
        UrlMapping saved = urlRepository.save(mapping);

        String shortCode = Base62Encoder.encode(saved.getId());
        saved.setShortCode(shortCode);
        UrlMapping finalMapping = urlRepository.save(saved);

        cache(shortCode, request.url());

        return toResponse(finalMapping);
    }

    @Transactional
    public String resolveUrl(String shortCode) {
        String cachedUrl = redisTemplate.opsForValue().get(cacheKey(shortCode));

        if (cachedUrl != null) {
            incrementClicks(shortCode);
            return cachedUrl;
        }

        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found: " + shortCode));

        cache(shortCode, mapping.getOriginalUrl());
        mapping.setClicks(mapping.getClicks() + 1);
        urlRepository.save(mapping);

        return mapping.getOriginalUrl();
    }

    @Transactional(readOnly = true)
    public UrlResponse getUrlInfo(String shortCode) {
        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found: " + shortCode));

        return toResponse(mapping);
    }

    private void incrementClicks(String shortCode) {
        urlRepository.findByShortCode(shortCode).ifPresent(mapping -> {
            mapping.setClicks(mapping.getClicks() + 1);
            urlRepository.save(mapping);
        });
    }

    private void cache(String shortCode, String originalUrl) {
        redisTemplate.opsForValue().set(
                cacheKey(shortCode),
                originalUrl,
                Duration.ofHours(24)
        );
    }

    private String cacheKey(String shortCode) {
        return "url:" + shortCode;
    }

    private UrlResponse toResponse(UrlMapping mapping) {
        return new UrlResponse(
                mapping.getOriginalUrl(),
                mapping.getShortCode(),
                "http://localhost:8080/" + mapping.getShortCode(),
                mapping.getCreatedAt(),
                mapping.getClicks()
        );
    }
}

package com.example.urlshortener.controller;

import com.example.urlshortener.dto.CreateUrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> create(@Valid @RequestBody CreateUrlRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(urlService.createShortUrl(request));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponse> getInfo(@PathVariable String shortCode) {
        return ResponseEntity.ok(urlService.getUrlInfo(shortCode));
    }
}

package com.example.urlshortener.controller;

import com.example.urlshortener.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortCode:[0-9a-zA-Z]+}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = urlService.resolveUrl(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(java.net.URI.create(originalUrl));

        return ResponseEntity.status(HttpStatus.FOUND)
                .headers(headers)
                .build();
    }
}

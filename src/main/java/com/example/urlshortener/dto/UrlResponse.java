package com.example.urlshortener.dto;

import java.time.LocalDateTime;

public record UrlResponse(
        String originalUrl,
        String shortCode,
        String shortUrl,
        LocalDateTime createdAt,
        Long clicks
) {}

package com.example.urlshortener.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "url_mappings", indexes = {
        @Index(name = "idx_short_code", columnList = "short_code", unique = true)
})
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false, columnDefinition = "TEXT")
    private String originalUrl;

    @Column(name = "short_code", nullable = false, unique = true, length = 10)
    private String shortCode;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Long clicks = 0L;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (clicks == null) clicks = 0L;
    }

    public Long getId() { return id; }
    public String getOriginalUrl() { return originalUrl; }
    public String getShortCode() { return shortCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getClicks() { return clicks; }

    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }
    public void setClicks(Long clicks) { this.clicks = clicks; }
}

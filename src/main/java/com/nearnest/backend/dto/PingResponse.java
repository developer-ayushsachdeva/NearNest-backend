package com.nearnest.backend.dto;

import com.nearnest.backend.entity.Ping;

import java.time.LocalDateTime;

public class PingResponse {

    private Long id;
    private Long userId;
    private String userName;

    private String category;
    private String title;
    private Double price;
    private String description;

    private Double latitude;
    private Double longitude;
    private Double radiusKm;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    private Integer likesCount;
    private Integer commentsCount;
    private Integer sharesCount;
    private Integer confirmationsCount;
    private Integer disputesCount;

    public PingResponse(Ping ping, String userName) {
        this.id = ping.getId();
        this.userId = ping.getUserId();
        this.userName = userName;

        this.category = ping.getCategory();
        this.title = ping.getTitle();
        this.price = ping.getPrice();
        this.description = ping.getDescription();

        this.latitude = ping.getLatitude();
        this.longitude = ping.getLongitude();
        this.radiusKm = ping.getRadiusKm();

        this.createdAt = ping.getCreatedAt();
        this.expiresAt = ping.getExpiresAt();

        this.likesCount = ping.getLikesCount();
        this.commentsCount = ping.getCommentsCount();
        this.sharesCount = ping.getSharesCount();
        this.confirmationsCount = ping.getConfirmationsCount();
        this.disputesCount = ping.getDisputesCount();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getRadiusKm() {
        return radiusKm;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public Integer getSharesCount() {
        return sharesCount;
    }

    public Integer getConfirmationsCount() {
        return confirmationsCount;
    }

    public Integer getDisputesCount() {
        return disputesCount;
    }
}
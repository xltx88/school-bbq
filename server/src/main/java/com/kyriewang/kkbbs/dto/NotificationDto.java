package com.kyriewang.kkbbs.dto;

import com.kyriewang.kkbbs.entity.Notification;

import java.util.List;

public class NotificationDto {
    private List<Notification> notificationDtos;
    private Integer currentPage;
    private Integer totalPages;
    // Getters and Setters
    public List<Notification> getNotificationDtos() {
        return notificationDtos;
    }

    public void setNotificationDtos(List<Notification> notificationDtos) {
        this.notificationDtos = notificationDtos;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
    
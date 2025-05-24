package com.kyriewang.kkbbs.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String name;
    private String password;
    private String avatarUrl;
    private Integer unreadCount;
    private String phonenb;
    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getPhonenb() {
        return phonenb;
    }

    public void setPhonenb(String phonenb) {
        this.phonenb = phonenb;
    }

    public User() {
    }

    public User(Long id, String name, String password, String avatarUrl, Integer unreadCount, String phonenb) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.unreadCount = unreadCount;
        this.phonenb = phonenb;
    }
}
    
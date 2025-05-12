package com.kyriewang.kkbbs.entity;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    private Long id;
    private Long outerid;
    private Long notifier;
    private String typename;
    private Integer status;
    private Date gmtCreate;
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOuterid() {
        return outerid;
    }

    public void setOuterid(Long outerid) {
        this.outerid = outerid;
    }

    public Long getNotifier() {
        return notifier;
    }

    public void setNotifier(Long notifier) {
        this.notifier = notifier;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
    
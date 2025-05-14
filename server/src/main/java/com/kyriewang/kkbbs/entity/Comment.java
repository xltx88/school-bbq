package com.kyriewang.kkbbs.entity;

import java.io.Serializable;

public class Comment implements Serializable {
    private Long id;
    private Long parent_id;
    private Integer type;
    private Long comment_creator;
    private Long receiver_id;
    private String receiver_name;
    private Long gmt_create;
    private Long gmt_modified;
    private Long like_count;
    private Integer comment_count;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getComment_creator() {
        return comment_creator;
    }

    public void setComment_creator(Long comment_creator) {
        this.comment_creator = comment_creator;
    }

    public Long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(Long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public Long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Long getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Long gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public Long getLike_count() {
        return like_count;
    }

    public void setLike_count(Long like_count) {
        this.like_count = like_count;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment() {
    }

    public Comment(Long id, Long parent_id, Integer type, Long comment_creator, Long receiver_id, String receiver_name, Long gmt_create, Long gmt_modified, Long like_count, Integer comment_count, String content) {
        this.id = id;
        this.parent_id = parent_id;
        this.type = type;
        this.comment_creator = comment_creator;
        this.receiver_id = receiver_id;
        this.receiver_name = receiver_name;
        this.gmt_create = gmt_create;
        this.gmt_modified = gmt_modified;
        this.like_count = like_count;
        this.comment_count = comment_count;
        this.content = content;
    }
}
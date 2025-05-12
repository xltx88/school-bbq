package com.kyriewang.kkbbs.dto;

public class queDto {
    private int id;
    private String title;
    private String description;

    private int creator;
    private long comment_count;
    private int view_count;
    private int like_count;
    private String tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public long getComment_count() {
        return comment_count;
    }

    public void setComment_count(long comment_count) {
        this.comment_count = comment_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public queDto() {
    }

    public queDto(int id, String title, String description, int creator, long comment_count, int view_count, int like_count, String tag) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.comment_count = comment_count;
        this.view_count = view_count;
        this.like_count = like_count;
        this.tag = tag;
    }
}

package com.jejunu.honjawan.vo;

public class AccompanyPostVO {
    private String id;
    private String uid;
    private String photoURL;
    private String username;
    private String startDate;
    private String endDate;
    private String tag;
    private String content;

    public AccompanyPostVO() {
    }

    public AccompanyPostVO(String id, String uid, String photoURL, String username, String startDate, String endDate, String tag, String content) {
        this.id = id;
        this.uid = uid;
        this.photoURL = photoURL;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tag = tag;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

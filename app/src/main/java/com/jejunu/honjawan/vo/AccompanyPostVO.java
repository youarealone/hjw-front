package com.jejunu.honjawan.vo;

import java.util.ArrayList;
import java.util.Date;

public class AccompanyPostVO {
    private String id;
    private String uid;
    private String photoURL;
    private String username;
    private Date startDate;
    private Date lastDate;
    private ArrayList<String> tags;
    private String content;

    public AccompanyPostVO() {
    }

    public AccompanyPostVO(String id, String uid, String photoURL, String username, Date startDate, Date lastDate, ArrayList<String> tags, String content) {
        this.id = id;
        this.uid = uid;
        this.photoURL = photoURL;
        this.username = username;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.tags = tags;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AccompanyPostVO{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", username='" + username + '\'' +
                ", startDate=" + startDate +
                ", lastDate=" + lastDate +
                ", tags=" + tags +
                ", content='" + content + '\'' +
                '}';
    }
}

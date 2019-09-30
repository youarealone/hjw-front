package com.example.hjw_front.vo;

import java.time.LocalDateTime;

public class ScheduleVO {
    private Integer id;
    private String content;
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ScheduleVO(Integer id, String content, String time) {
        this.id = id;
        this.content = content;
        this.time = time;
    }
}

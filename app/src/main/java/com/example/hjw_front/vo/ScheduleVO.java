package com.example.hjw_front.vo;

public class ScheduleVO {
    private Integer id;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minutes;
    private String content;

    public ScheduleVO() {
    }

    public ScheduleVO(Integer id, Integer year, Integer month, Integer day, Integer hour, Integer minutes, String content) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + year + "/" + month + "/" + day + " " + hour + ":" + minutes + " " + content;
    }
}

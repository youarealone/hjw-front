package com.example.hjw_front.repositories;

public class ScheduleRepository {
    private static ScheduleRepository instance;

    private ScheduleRepository() { }
    public static ScheduleRepository getInstance() {
        if (instance == null) instance = new ScheduleRepository();
        return instance;
    }

    public void create(Integer year, Integer month, Integer day, Integer hour, Integer minutes, String content) {

    }
}

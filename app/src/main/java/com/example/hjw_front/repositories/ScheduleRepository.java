package com.example.hjw_front.repositories;

import com.example.hjw_front.vo.ScheduleVO;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleRepository {
    private static ScheduleRepository instance;
    private CollectionReference reference;

    private ScheduleRepository() {
        reference = FirebaseFirestore.getInstance().collection("schedules");
    }
    public static ScheduleRepository getInstance() {
        if (instance == null) instance = new ScheduleRepository();
        return instance;
    }

    public void create(String uid, Integer year, Integer month, Integer day, Integer hour, Integer minutes, String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        map.put("hour", hour);
        map.put("minutes", minutes);
        map.put("content", content);

        reference.add(map);
    }
}

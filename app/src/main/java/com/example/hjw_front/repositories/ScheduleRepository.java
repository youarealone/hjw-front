package com.example.hjw_front.repositories;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class ScheduleRepository {
    private final String TAG = "[ScheduleRepository]";
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

    public Task<QuerySnapshot> findByUidAndDate(String uid, Integer year, Integer month, Integer day) {
        return reference
                .whereEqualTo("uid", uid)
                .whereEqualTo("year", year)
                .whereEqualTo("month", month)
                .whereEqualTo("day", day)
                .orderBy("hour")
                .orderBy("minutes")
                .get();
    }

    public void delete(String id) {
        reference.document(id).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, id + " 삭제 완료");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, id + " 삭제 실패", e);
                    }
                });
    }
}

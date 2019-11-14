package com.jejunu.honjawan.repositories;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jejunu.honjawan.vo.AccompanyPostVO;

import java.util.HashMap;
import java.util.Map;

public class AccompanyPostRepository {
    private final String TAG = "[AccompanyPostRepo]";
    private static AccompanyPostRepository instance;
    private CollectionReference reference;

    private AccompanyPostRepository() {
        reference = FirebaseFirestore.getInstance().collection("accompany-posts");
    }

    public static AccompanyPostRepository getInstance() {
        if (instance == null) instance = new AccompanyPostRepository();
        return instance;
    }

    public void create(AccompanyPostVO accompanyPostVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", accompanyPostVO.getUid());
        map.put("photoURL", accompanyPostVO.getPhotoURL());
        map.put("username", accompanyPostVO.getUsername());
        map.put("startDate", accompanyPostVO.getStartDate());
        map.put("lastDate", accompanyPostVO.getLastDate());
        map.put("tags", accompanyPostVO.getTags());
        map.put("content", accompanyPostVO.getContent());
        Log.d(TAG, map.toString());

        reference.add(map);
    }

}

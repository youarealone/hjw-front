package com.example.hjw_front.repositories;

import android.util.Log;

import com.example.hjw_front.vo.AccompanyPostVO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

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
//        map.put("photoURL", accompanyPostVO.getPhotoURL());
//        map.put("username", accompanyPostVO.getUsername());
//        map.put("startDate", accompanyPostVO.getStartDate());
//        map.put("lastDate", accompanyPostVO.getLastDate());
//        map.put("tags", accompanyPostVO.getTags());
        map.put("content", accompanyPostVO.getContent());
        Log.d(TAG, map.toString());

        reference.add(map);
    }

}

package com.example.hjw_front.repositories;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SOSRepository {
    private CollectionReference reference;
    private static SOSRepository instance;

    private SOSRepository() {
        reference = FirebaseFirestore.getInstance().collection("sos");
    }

    public static SOSRepository getInstance() {
        if (instance == null) {
            instance = new SOSRepository();
        }
        return instance;
    }

    public void create(String uid, String name, String contract) {
        Map<String, Object> sos = new HashMap<>();
        sos.put("uid", uid);
        sos.put("name", name);
        sos.put("contract", contract);

        reference.add(sos);
    }
}

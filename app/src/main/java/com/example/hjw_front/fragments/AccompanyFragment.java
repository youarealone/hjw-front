package com.example.hjw_front.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hjw_front.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccompanyFragment extends Fragment {
    String mockName, mockEmail, mockPhotoURL, mockUid;
    private FirebaseUser currentUser;

    public AccompanyFragment() {
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();

        this.mockEmail = currentUser.getEmail();
        this.mockName = currentUser.getDisplayName();
        this.mockPhotoURL = currentUser.getPhotoUrl().toString();
        this.mockUid = currentUser.getUid();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accompany, container, false);
        return view;
    }
}

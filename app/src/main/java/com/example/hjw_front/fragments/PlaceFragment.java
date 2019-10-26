package com.example.hjw_front.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hjw_front.R;
import com.example.hjw_front.adapters.BookmarkAdapter;
import com.example.hjw_front.adapters.PlaceAdapter;

public class PlaceFragment extends Fragment {
    RecyclerView placeView;
    PlaceAdapter placeadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_place, container, false);
        placeView = view.findViewById(R.id.placeView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        placeView.setLayoutManager(linearLayoutManager);
        placeView.hasFixedSize();
        placeadapter = new PlaceAdapter();
        placeView.setAdapter(placeadapter);
        return view;
    }
}

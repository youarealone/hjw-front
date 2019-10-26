package com.example.hjw_front.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hjw_front.R;
import com.example.hjw_front.adapters.BookmarkAdapter;
import com.example.hjw_front.adapters.PlaceAdapter;

import java.util.List;

public class PlaceFragment extends Fragment {
    RecyclerView placeView;
    PlaceAdapter placeadapter;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_place, container, false);
        placeView = view.findViewById(R.id.placeView);
        spinner = view.findViewById(R.id.spinner_field);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        placeView.setLayoutManager(linearLayoutManager);
        placeView.hasFixedSize();
        placeadapter = new PlaceAdapter();
        placeView.setAdapter(placeadapter);
        String[] str = getResources().getStringArray(R.array.area);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.item_spiner, str);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getSelectedItemPosition() > 0) {

                    //선택된 항목

                    Log.v("알림", spinner.getSelectedItem().toString() + "is selected");

                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }
}

package com.example.hjw_front;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hjw_front.adapters.ScheduleListAdapter;
import com.example.hjw_front.vo.ScheduleVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        ArrayList<ScheduleVO> mockList = makeList();

        ScheduleListAdapter adapter = new ScheduleListAdapter();
        ListView listView = view.findViewById(R.id.lv_schedule);
        listView.setAdapter(adapter);

        for (ScheduleVO vo: mockList) {
            adapter.addItem(vo);
        }

        return view;
    }

    private ArrayList<ScheduleVO> makeList() {
        ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
        for (int i = 0; i < 5; i++) {
            ScheduleVO vo = new ScheduleVO(i, "카멜리아힐"+i, "5/12 1"+i+":00");
            list.add(vo);
        }
        return list;
    }
}

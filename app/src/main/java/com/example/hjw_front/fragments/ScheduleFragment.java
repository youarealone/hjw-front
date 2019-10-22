package com.example.hjw_front.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hjw_front.R;
import com.example.hjw_front.adapters.ScheduleListAdapter;
import com.example.hjw_front.repositories.ScheduleRepository;
import com.example.hjw_front.vo.ScheduleVO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ScheduleFragment extends Fragment {
    ScheduleRepository repository;
    FirebaseUser currentUser;
    private ScheduleListAdapter adapter;
    private ArrayList<ScheduleVO> mySchedules;

    Calendar dateCalendar = Calendar.getInstance();
    private int sYear;
    private int sMonth;
    private int sDay;

    DatePickerDialog.OnDateSetListener myDatePicker;
    private TextView tvToday;

    public ScheduleFragment() {
        this.repository = ScheduleRepository.getInstance();
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
        this.mySchedules = new ArrayList<>();
        myDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                sYear = year;
                sMonth = month + 1;
                sDay = dayOfMonth;

                dateCalendar.set(Calendar.YEAR, year);
                dateCalendar.set(Calendar.MONTH, month);
                dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        adapter = new ScheduleListAdapter(mySchedules);
        ListView listView = view.findViewById(R.id.lv_schedule);
        listView.setAdapter(adapter);
        listMySchedule(currentUser.getUid());

        tvToday = view.findViewById(R.id.tv_today);
        updateLabel();

        for (ScheduleVO vo: mySchedules) {
            adapter.addItem(vo);
        }

        view.findViewById(R.id.btn_open_add_schedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddScheduleDialogFragment dialogFragment = new AddScheduleDialogFragment();
                dialogFragment.show(getFragmentManager(), AddScheduleDialogFragment.TAG);
            }
        });

        return view;
    }

    private void listMySchedule(String uid) {
        this.repository.findByUid(uid)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            for (QueryDocumentSnapshot document: task.getResult()) {
                                ScheduleVO scheduleVO = document.toObject(ScheduleVO.class);
                                scheduleVO.setId(document.getId());
                                Log.d("[ScheduleFragment]", scheduleVO.toString());
                                if (!mySchedules.contains(scheduleVO)) {
                                    mySchedules.add(scheduleVO);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        tvToday.setText(sdf.format(dateCalendar.getTime()));
    }
}

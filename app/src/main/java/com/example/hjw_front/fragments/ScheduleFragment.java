package com.example.hjw_front.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import androidx.core.app.Fragment;
import androidx.core.widget.SwipeRefreshLayout;

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

public class ScheduleFragment extends Fragment implements View.OnClickListener {
    private final String TAG = "[SceduleFragment]";
    ScheduleRepository repository;
    FirebaseUser currentUser;
    private ScheduleListAdapter adapter;
    private ArrayList<ScheduleVO> mySchedules;

    Calendar dateCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker;
    private Button btnToday;
    private SwipeRefreshLayout refreshLayout;

    public ScheduleFragment() {
        this.repository = ScheduleRepository.getInstance();
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
        this.mySchedules = new ArrayList<ScheduleVO>();
        myDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateCalendar.set(Calendar.YEAR, year);
                dateCalendar.set(Calendar.MONTH, month);
                dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                listMySchedule(currentUser.getUid());
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        refreshLayout = view.findViewById(R.id.layout_schedule_container);

        initList(view);
        setListeners(view);

        btnToday = view.findViewById(R.id.btn_today);
        updateLabel();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_add_schedule:
                AddScheduleDialogFragment dialogFragment = new AddScheduleDialogFragment();
                dialogFragment.show(getFragmentManager(), AddScheduleDialogFragment.TAG);
                break;

            case R.id.btn_today:
                new DatePickerDialog(getContext()
                        , myDatePicker
                        , dateCalendar.get(Calendar.YEAR)
                        , dateCalendar.get(Calendar.MONTH)
                        , dateCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
                break;

            default:
                break;
        }
    }

    private void initList(View view) {
        adapter = new ScheduleListAdapter(mySchedules);
        ListView listView = view.findViewById(R.id.lv_schedule);
        listView.setAdapter(adapter);
        listMySchedule(currentUser.getUid());
    }

    private void setListeners(View view) {
        view.findViewById(R.id.btn_open_add_schedule).setOnClickListener(this);
        view.findViewById(R.id.btn_today).setOnClickListener(this);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listMySchedule(currentUser.getUid());

                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void listMySchedule(String uid) {
        mySchedules.clear();
        this.repository.findByUidAndDate(
                uid
                , dateCalendar.get(Calendar.YEAR)
                , dateCalendar.get(Calendar.MONTH) + 1
                , dateCalendar.get(Calendar.DAY_OF_MONTH))
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        for (QueryDocumentSnapshot document: task.getResult()) {
                            ScheduleVO scheduleVO = document.toObject(ScheduleVO.class);
                            scheduleVO.setId(document.getId());
                            if (!mySchedules.contains(scheduleVO)) {
                                mySchedules.add(scheduleVO);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        adapter.notifyDataSetChanged();

    }

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        btnToday.setText(sdf.format(dateCalendar.getTime()));
    }
}

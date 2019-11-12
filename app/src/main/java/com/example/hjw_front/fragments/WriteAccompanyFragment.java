package com.example.hjw_front.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hjw_front.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WriteAccompanyFragment extends Fragment implements View.OnClickListener {
    private TextView tvStartDate;
    private TextView tvLastDate;

    private Button btnAccompany;
    private Button btnStay;
    private Button btnMeal;
    private Button btnTaxi;

    private Calendar startCalendar, lastCalendar;
    private DatePickerDialog.OnDateSetListener startDatePicker, lastDatePicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_accompany, container, false);

        initViews(view);
        setListeners(view);
        setCalendars();

        return view;
    }

    private void initViews(View view) {
        tvStartDate = view.findViewById(R.id.tv_write_accompany_start);
        tvLastDate = view.findViewById(R.id.tv_write_accompany_last);
    }

    private void setListeners(View view) {
        tvStartDate.setOnClickListener(this);
        tvLastDate.setOnClickListener(this);
        view.findViewById(R.id.iv_write_accompany_close).setOnClickListener(this);
    }

    private void setCalendars() {
        startCalendar = Calendar.getInstance();
        startDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startCalendar.set(Calendar.YEAR, year);
                startCalendar.set(Calendar.MONTH, month);
                startCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yy.MM.dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

                tvStartDate.setText(sdf.format(startCalendar.getTime()));
            }
        };

        lastCalendar = Calendar.getInstance();
        lastDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                lastCalendar.set(Calendar.YEAR, year);
                lastCalendar.set(Calendar.MONTH, month);
                lastCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yy.MM.dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

                tvLastDate.setText(sdf.format(lastCalendar.getTime()));
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_write_accompany_start:
                new DatePickerDialog(getContext()
                        , startDatePicker
                        , startCalendar.get(Calendar.YEAR)
                        , startCalendar.get(Calendar.MONTH)
                        , startCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
                break;

            case R.id.tv_write_accompany_last:
                new DatePickerDialog(getContext()
                        , lastDatePicker
                        , lastCalendar.get(Calendar.YEAR)
                        , lastCalendar.get(Calendar.MONTH)
                        , lastCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
                break;

            case R.id.iv_write_accompany_close:
                getActivity().onBackPressed();
                break;

            default:
                break;
        }
    }
}

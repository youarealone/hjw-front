package com.example.hjw_front.fragments;

import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
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

    private ArrayList<String> selectedTags;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_accompany, container, false);
        selectedTags = new ArrayList<String>();

        initViews(view);
        setListeners(view);
        setCalendars();

        return view;
    }

    private void initViews(View view) {
        tvStartDate = view.findViewById(R.id.tv_write_accompany_start);
        tvLastDate = view.findViewById(R.id.tv_write_accompany_last);

        btnAccompany = view.findViewById(R.id.btn_write_accompany_tag_accompany);
        btnStay = view.findViewById(R.id.btn_write_accompany_tag_stay);
        btnMeal = view.findViewById(R.id.btn_write_accompany_tag_meal);
        btnTaxi = view.findViewById(R.id.btn_write_accompany_tag_taxi);
    }

    private void setListeners(View view) {
        tvStartDate.setOnClickListener(this);
        tvLastDate.setOnClickListener(this);

        view.findViewById(R.id.btn_write_accompany_write).setOnClickListener(this);
        view.findViewById(R.id.iv_write_accompany_close).setOnClickListener(this);

        btnAccompany.setOnClickListener(this);
        btnStay.setOnClickListener(this);
        btnMeal.setOnClickListener(this);
        btnTaxi.setOnClickListener(this);
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

            case R.id.btn_write_accompany_tag_accompany:
                tagToggle(btnAccompany, "accompany");
                break;

            case R.id.btn_write_accompany_tag_stay:
                tagToggle(btnStay, "stay");
                break;

            case R.id.btn_write_accompany_tag_meal:
                tagToggle(btnMeal, "meal");
                break;

            case R.id.btn_write_accompany_tag_taxi:
                tagToggle(btnTaxi, "taxi");
                break;

            case R.id.btn_write_accompany_write:
                break;

            case R.id.iv_write_accompany_close:
                getActivity().onBackPressed();
                break;

            default:
                break;
        }
    }

    private void tagToggle(Button btn, String tag) {
        if (selectedTags.contains(tag)) {
            selectedTags.remove(tag);
            btn.setBackgroundResource(R.drawable.gray_lable);
        } else {
            selectedTags.add(tag);
            btn.setBackgroundResource(R.drawable.green_label);
        }
    }
}

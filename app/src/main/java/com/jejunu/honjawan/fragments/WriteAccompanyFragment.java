package com.jejunu.honjawan.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jejunu.honjawan.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WriteAccompanyFragment extends Fragment implements View.OnClickListener {
    private TextView tvStartDate;
    private TextView tvEndDate;

    private Calendar startCalendar, endCalendar;
    private DatePickerDialog.OnDateSetListener startDatePicker, endDatePicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_accompany, container, false);

        tvStartDate = view.findViewById(R.id.tv_write_accompany_start);
        tvEndDate = view.findViewById(R.id.tv_write_accompany_end);

        tvStartDate.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);
        view.findViewById(R.id.iv_write_accompany_close).setOnClickListener(this);

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

        endCalendar = Calendar.getInstance();
        endDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endCalendar.set(Calendar.YEAR, year);
                endCalendar.set(Calendar.MONTH, month);
                endCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yy.MM.dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

                tvEndDate.setText(sdf.format(endCalendar.getTime()));
            }
        };

        return view;
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

            case R.id.tv_write_accompany_end:
                new DatePickerDialog(getContext()
                        , endDatePicker
                        , endCalendar.get(Calendar.YEAR)
                        , endCalendar.get(Calendar.MONTH)
                        , endCalendar.get(Calendar.DAY_OF_MONTH)
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

package com.example.hjw_front.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hjw_front.R;
import com.example.hjw_front.repositories.ScheduleRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddScheduleDialogFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "add_schedule";
    private int sYear, sMonth, sDay;
    private int sHour, sMinutes;
    private String content;

    Calendar myCalendar = Calendar.getInstance();
    ScheduleRepository repository = ScheduleRepository.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            sYear = year;
            sMonth = month + 1;
            sDay = dayOfMonth;

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_add_schedule, container, false);

        setCancelable(false);

        view.findViewById(R.id.btn_add_schedule).setOnClickListener(this);
        view.findViewById(R.id.btn_dismiss_schedule_dialog).setOnClickListener(this);

        TextView tvDate = view.findViewById(R.id.tv_schedule_date);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final TextView tvTime = view.findViewById(R.id.tv_schedule_time);
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        sHour = selectedHour;
                        sMinutes = selectedMinute;
                        String state = "AM";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        // EditText에 출력할 형식 지정
                        tvTime.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_schedule:
                content = ((EditText) view.findViewById(R.id.et_schedule_content)).getText().toString();
                repository.create(sYear, sMonth, sDay, sHour, sMinutes, content);
                Toast.makeText(getContext(), "일정이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                dismiss();
                break;

            case R.id.btn_dismiss_schedule_dialog:
                dismiss();
                break;

            default:
                break;
        }
    }

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        TextView tvDate = view.findViewById(R.id.tv_schedule_date);
        tvDate.setText(sdf.format(myCalendar.getTime()));
    }
}

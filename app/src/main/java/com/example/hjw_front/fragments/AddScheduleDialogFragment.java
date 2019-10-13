package com.example.hjw_front.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hjw_front.R;

public class AddScheduleDialogFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "add_schedule";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_schedule, container, false);

        setCancelable(false);

        view.findViewById(R.id.btn_add_schedule).setOnClickListener(this);
        view.findViewById(R.id.btn_dismiss_schedule_dialog).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_schedule:
                Toast.makeText(getContext(), "애드", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_dismiss_schedule_dialog:
                Toast.makeText(getContext(), "취소", Toast.LENGTH_LONG).show();
                dismiss();
                break;

            default:
                break;
        }
    }
}

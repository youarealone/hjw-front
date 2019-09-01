package com.example.hjw_front;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjw_front.utils.FragmentChanger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.example.hjw_front.R.drawable.speaker;
import static com.example.hjw_front.R.drawable.speaker_mute;
import static com.example.hjw_front.R.drawable.speaker_vibration;

public class SosFragment extends Fragment {
    FragmentChanger fragmentChanger = null;
    final String[] list_sound = {"소리", "진동", "무음"};
    private int index;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentChanger = new FragmentChanger(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sos, container, false);

        Button btn_sound = view.findViewById(R.id.btn_sound);
        ImageView imageView = view.findViewById(R.id.img_sound);

        index = 0;

        view.findViewById(R.id.textView5).setOnClickListener(v -> fragmentChanger.changeFragment(new ContractFragment()));

        btn_sound.setOnClickListener(v -> {
            index = index != list_sound.length - 1 ? index + 1 : 0;
            if (index == 0) {
                imageView.setImageResource(speaker);
            } else if (index == 1) {
                imageView.setImageResource(speaker_vibration);
            } else if (index == 2) {
                imageView.setImageResource(speaker_mute);
            }
            btn_sound.setText(list_sound[index]);
        });

        return view;
    }


}

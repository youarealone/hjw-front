package com.example.hjw_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hjw_front.utils.FragmentChanger;

public class SosFragment extends Fragment {
    private TextView textView;
    FragmentChanger fragmentChanger = null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sos, container, false);

        textView = view.findViewById(R.id.textView5);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentChanger.changeFragment(new ContractFragment());
            }
        });

        return view;
    }



}

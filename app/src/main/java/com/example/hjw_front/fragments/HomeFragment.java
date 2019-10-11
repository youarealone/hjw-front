package com.example.hjw_front.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hjw_front.R;
import com.example.hjw_front.utils.FragmentChanger;

public class HomeFragment extends Fragment {
    private FragmentChanger fragmentChanger = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentChanger = new FragmentChanger(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.container_search).setOnClickListener(v -> fragmentChanger.changeFragment(new SearchFragment()));

        view.findViewById(R.id.btn_userInfo).setOnClickListener(v -> fragmentChanger.changeFragment(new UserInfoFragment()));

        return view;
    }
}

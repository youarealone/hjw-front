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

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentChanger fragmentChanger = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentChanger = new FragmentChanger(getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.container_search).setOnClickListener(this);
        view.findViewById(R.id.iv_home_editorpick1).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.container_search:
                fragmentChanger.changeFragment(new SearchFragment());
                break;

            case R.id.iv_home_editorpick1:
                fragmentChanger.changeFragment(new EditorPick1Fragment());
                break;

            default:
                break;
        }
    }
}

package com.example.hjw_front.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.hjw_front.R;

public class FragmentChanger {
    private FragmentManager fragmentManager;

    public FragmentChanger(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

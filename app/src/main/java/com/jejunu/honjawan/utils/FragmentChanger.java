package com.jejunu.honjawan.utils;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jejunu.honjawan.R;

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

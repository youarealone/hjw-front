package com.jejunu.honjawan.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionChecker {
    private Activity activity;

    public PermissionChecker(Activity activity) {
        this.activity = activity;
    }

    public void permissionCheck() {
        if (!isPermit()) {
            ActivityCompat.requestPermissions(activity,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    public boolean isPermit() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

}

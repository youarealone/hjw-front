package com.jejunu.honjawan.utils;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;

/*
[참고] MD5, SHA1, SHA256 키 알아보기
$ keytool -list -v -alias androiddebugkey -keystore %USERPROFILE%\.android\debug.keystore
키 저장소 비밀번호 : (공백)
 */

public class KeyHashGetter {
    public static void printKeyHash(Activity activity) {
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo("com.example.hjw_front", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

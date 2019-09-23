package com.example.hjw_front;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hjw_front.utils.FragmentChanger;

import java.util.ArrayList;
import java.util.List;

public class ContractFragment extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private FloatingActionButton fab_contract;
    private String name;
    private String num;
    private RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        fab_contract = view.findViewById(R.id.fab_contract);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        //layoutManager = new GridLayoutManager(this, 1);
        layoutManager = new LinearLayoutManager(view.getContext());


        recyclerView.setLayoutManager(layoutManager);



        adapter = new RecyclerViewA();
        recyclerView.setAdapter(adapter);
        fab_contract.setOnClickListener(view1 -> {
            // 연락처 퍼미션 체크
            if (ContextCompat.checkSelfPermission(view.getContext(),
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {

                // 퍼미션이 없을경우 실행되는 로직
                if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                        Manifest.permission.READ_CONTACTS)) {
                } else {
                    // 퍼미션 리퀘스트함수 호출
                    ActivityCompat.requestPermissions(this.getActivity(),
                            new String[]{Manifest.permission.READ_CONTACTS},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                }
            } else {
                // 퍼미션이 있을경우 실행되는 로직
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Cursor cursor = getContext().getContentResolver().query(data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                            , ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            cursor.moveToFirst();
            name = cursor.getString(0);
            num = cursor.getString(1);

            cursor.close();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
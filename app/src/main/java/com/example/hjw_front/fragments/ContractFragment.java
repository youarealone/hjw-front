package com.example.hjw_front.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.Fragment;
import androidx.core.content.ContextCompat;

import com.example.hjw_front.R;
import com.example.hjw_front.adapters.SosAdapter;
import com.example.hjw_front.repositories.SOSRepository;
import com.example.hjw_front.vo.SosContractVO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;
import java.util.List;

public class ContractFragment extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private String name;
    private String num;
    private RecyclerView list_sos_view;
    private SosAdapter adapter;
    private List<SosContractVO> sosContractList = new LinkedList<>();
    private FirebaseUser currentUser;
    private SOSRepository sosRepository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        FloatingActionButton fab_contract = view.findViewById(R.id.fab_contract);
        list_sos_view = view.findViewById(R.id.sos_list_view);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        init_sos();
        listMyContract();

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

    private void init_sos() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        list_sos_view.setLayoutManager(linearLayoutManager);
        list_sos_view.hasFixedSize();
        adapter = new SosAdapter(sosContractList);
        list_sos_view.setAdapter(adapter);
        sosRepository = SOSRepository.getInstance();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            //연락처 데이터 가져오기
            Cursor cursor = getContext().getContentResolver().query(data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                            , ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);

            cursor.moveToFirst();

            name = cursor.getString(0);
            num = cursor.getString(1);
            SosContractVO sosContractVO = new SosContractVO(null, currentUser.getUid(), num, name);

            sosRepository.create(currentUser.getUid(), name, num);

            if (sosContractList.contains(sosContractVO)) {
                Toast.makeText(this.getContext(), "중복된 연락처가 있습니다.", Toast.LENGTH_LONG).show();
            } else {
                listMyContract();
            }
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

    private void listMyContract() {
        sosRepository.findByUID(currentUser.getUid())
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            for (QueryDocumentSnapshot document: task.getResult()) {
                                SosContractVO sosContractVO = document.toObject(SosContractVO.class);
                                sosContractVO.setId(document.getId());
                                if (!sosContractList.contains(sosContractVO)) {
                                    sosContractList.add(sosContractVO);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }
}
package com.example.hjw_front;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class contract_loader {

    public static List<contract_data> getData(Context context) {
        List<contract_data> datas = new ArrayList<>();

        //전화번호부를 가져오기위해 context를 사용하,커넥터 역할
        ContentResolver resolver = context.getContentResolver();
        // 전화번호 데이터가 있는 테이블 주소 가져오기
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // 테이블에서 가져올 칼럼 정의
        String proj[] = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        //컨텐트 리졸버로 데이터 가져오기.

        Cursor cursor = resolver.query(phoneUri, proj, null, null, null);

        //cusorr에 데이터 존재여부

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(proj[0]);
                int id = cursor.getInt(index);

                index = cursor.getColumnIndex(proj[1]);
                String name = cursor.getString(index);

                index = cursor.getColumnIndex(proj[2]);
                String tel = cursor.getString(index);

                contract_data data = new contract_data();
                data.setId(id);
                data.setName(name);
                data.setTel(tel);

                // 데이터 저장
                datas.add(data);

            }
        }
        cursor.close();

        return datas;
    }
}

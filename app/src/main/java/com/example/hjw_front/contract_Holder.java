package com.example.hjw_front;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class contract_Holder extends RecyclerView.ViewHolder {
    private TextView textTel;
    private TextView textname;

    public contract_Holder(View view) {
        super(view);
        // 생성자 생성될때 xml에 있는 위젯을 소스코드와 연결
        textname = view.findViewById(R.id.textView7);
        textTel = view.findViewById(R.id.textView3);

    }

    public String getTextTel() {
        return textTel.getText().toString();
    }

    public void setTextTel(String value) {
        textTel.setText(value);
    }

    public String getTextname() {
        return textname.getText().toString();
    }

    public void setTextname(String value) {
        textname.setText(value);
    }
}

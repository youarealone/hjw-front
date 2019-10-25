package com.example.hjw_front.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hjw_front.R;
import com.example.hjw_front.vo.AccompanyPostVO;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class AccompanyAdapter extends BaseAdapter {
    private ArrayList<AccompanyPostVO> list;

    public AccompanyAdapter(ArrayList<AccompanyPostVO> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_accompany, parent, false);
        }

        ImageView ivProfilePhoto = convertView.findViewById(R.id.iv_profile_photo);
        TextView tvUsername = convertView.findViewById(R.id.tv_username);
        TextView tvStartDate = convertView.findViewById(R.id.tv_accompany_start);
        TextView tvEndDate = convertView.findViewById(R.id.tv_accompany_end);
        Button btnLabel = convertView.findViewById(R.id.btn_accompany_label);
        TextView tvContent = convertView.findViewById(R.id.tv_accompany_content);

        AccompanyPostVO vo = list.get(position);

        Glide.with(convertView).load(vo.getPhotoURL()).into(ivProfilePhoto);
        tvUsername.setText(vo.getUsername());
        tvStartDate.setText(vo.getStartDate());
        tvEndDate.setText(vo.getEndDate());
        btnLabel.setText(vo.getTag());
        tvContent.setText(vo.getContent());

        return convertView;
    }

    public void addItem(AccompanyPostVO vo) {
        this.list.add(vo);
    }
}

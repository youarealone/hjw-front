package com.jejunu.honjawan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jejunu.honjawan.R;
import com.jejunu.honjawan.utils.DateFormatter;
import com.jejunu.honjawan.vo.AccompanyPostVO;

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
        TextView tvLabelAccompany = convertView.findViewById(R.id.tv_accompany_label_accompany);
        TextView tvLabelStay = convertView.findViewById(R.id.tv_accompany_label_stay);
        TextView tvLabelMeal = convertView.findViewById(R.id.tv_accompany_label_meal);
        TextView tvLabelTaxi = convertView.findViewById(R.id.tv_accompany_label_taxi);
        TextView tvContent = convertView.findViewById(R.id.tv_accompany_content);

        AccompanyPostVO vo = list.get(position);

        Glide.with(convertView).load(vo.getPhotoURL()).into(ivProfilePhoto);
        tvUsername.setText(vo.getUsername());
        tvStartDate.setText(DateFormatter.format(vo.getStartDate(), "yy.MM.dd"));
        tvEndDate.setText(DateFormatter.format(vo.getLastDate(), "yy.MM.dd"));
        tvContent.setText(vo.getContent());

        for (String tag: vo.getTags()) {
            switch (tag) {
                case "#동행":
                    tvLabelAccompany.setVisibility(View.VISIBLE);
                    break;

                case "#숙박":
                    tvLabelStay.setVisibility(View.VISIBLE);
                    break;

                case "#겸상":
                    tvLabelMeal.setVisibility(View.VISIBLE);
                    break;

                case "#택시":
                    tvLabelTaxi.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }
        }

        return convertView;
    }

    public void addItem(AccompanyPostVO vo) {
        this.list.add(vo);
    }
}

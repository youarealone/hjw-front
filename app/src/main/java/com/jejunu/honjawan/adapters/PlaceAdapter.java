package com.jejunu.honjawan.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jejunu.honjawan.R;
import com.jejunu.honjawan.vo.PlaceVO;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<PlaceVO> placevo;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_place, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView placename, placeAddress, placePrice, placePriceOrigin;
        private ImageView placeImage;
        private Button placeSubmit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placename = itemView.findViewById(R.id.placeName);
            placeAddress = itemView.findViewById(R.id.placeWhere);
            placeImage = itemView.findViewById(R.id.placeImage);
            placePriceOrigin = itemView.findViewById(R.id.placePriceOrigin);
            placePrice = itemView.findViewById(R.id.placePrice);
            placeSubmit = itemView.findViewById(R.id.placeSubmit);

            placePriceOrigin.setPaintFlags(placePriceOrigin.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        }
    }
}

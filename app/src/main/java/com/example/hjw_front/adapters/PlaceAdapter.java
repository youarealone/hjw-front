package com.example.hjw_front.adapters;

import android.content.ClipData;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjw_front.R;
import com.example.hjw_front.vo.PlaceVO;

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

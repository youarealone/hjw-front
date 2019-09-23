package com.example.hjw_front;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class RecyclerViewA extends RecyclerView.Adapter<RecyclerViewA.ViewHolder> {

    @NonNull
    @Override
    public RecyclerViewA.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contract,viewGroup,false);
        RecyclerViewA.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewA.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText("me");
        viewHolder.textView2.setText("ko");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public TextView textView2;


        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.item_num);
            this.textView2 = view.findViewById(R.id.item_name);

        }
    }
}

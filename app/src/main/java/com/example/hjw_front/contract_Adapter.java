package com.example.hjw_front;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class contract_Adapter extends RecyclerView.Adapter<contract_Holder> {

    List<contract_data> datas;

    public contract_Adapter(List<contract_data> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public contract_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_contract, parent, false);
        return new contract_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contract_Holder holder, int position) {
        contract_data data = datas.get(position);
        holder.setTextname(data.getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

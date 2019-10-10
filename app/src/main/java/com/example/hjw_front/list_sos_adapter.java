package com.example.hjw_front;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class list_sos_adapter extends RecyclerView.Adapter<list_sos_adapter.ItemViewHolder> {

    private static List<SosContract> sosContracts;

    public list_sos_adapter(List<SosContract> sosContracts) {
        this.sosContracts = sosContracts;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //LayoutInflater를 활용해 전 단계에서 만들었던 item_contract.xml 를 인플레이트.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contract, parent, false);

        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // 아이템을 한개, 한개 씩 보여주는 함수
        holder.onBind(sosContracts.get(position));
        holder.btn_rm_sos.setOnClickListener(view -> {
            sosContracts.remove(position);
//            notifyItemChanged(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        // 뷰 아이템의 갯수;
        return sosContracts.size();
    }

//    void addItem(SosContract SosContract) {
//        //외부에서 아이템 추가시키는 함수
//        sosContracts.add(SosContract);
//    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView list_name;
        private TextView list_number;
        private Button btn_rm_sos;
        ItemViewHolder(View itemView) {
            super(itemView);
            btn_rm_sos = itemView.findViewById(R.id.btn_list_rm);
            list_name = itemView.findViewById(R.id.item_name);
            list_number = itemView.findViewById(R.id.item_num);
        }

        public void onBind(SosContract SosContract) {
            list_name.setText(SosContract.getName());
            list_number.setText(SosContract.getContract());
        }
    }
}

package com.example.hjw_front;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.itemViewHolder> {

    private static List<BookmarkMember> bk_members;

//    public BookmarkAdapter(List<BookmarkMember> bk_members) {
//        this.bk_members = bk_members;
//    }

    public BookmarkAdapter() {

    }
    @NonNull
    @Override
    public BookmarkAdapter.itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark, parent, false);
        Log.d("!!!","나왓습니다");
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.itemViewHolder itemViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class itemViewHolder extends RecyclerView.ViewHolder {

        private TextView discount;
        private TextView price;
        private TextView submit;
        private TextView total;
        private Button del_bk;
        private ImageView bk_img;

        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
//            del_bk = itemView.findViewById(R.id.bk_del);
//            price = itemView.findViewById(R.id.bk_price);
//            discount = itemView.findViewById(R.id.bk_discount);
//            submit = itemView.findViewById(R.id.bk_submit);
//            total = itemView.findViewById(R.id.bk_total);
//            bk_img = itemView.findViewById(R.id.bk_img);
//            del_bk.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    BookmarkMember member =new BookmarkMember();
//                    bk_members.add(member);
//                }
//            });

        }

        public void onBind(BookmarkMember member){

        }
    }
}

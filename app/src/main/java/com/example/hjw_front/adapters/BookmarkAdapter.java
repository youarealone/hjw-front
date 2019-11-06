package com.example.hjw_front.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hjw_front.R;
import com.example.hjw_front.vo.BookmarkVO;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.itemViewHolder> {

    private static List<BookmarkVO> bk_members;

//    public BookmarkAdapter(List<BookmarkVO> bk_members) {
//        this.bk_members = bk_members;
//    }

    public BookmarkAdapter() {

    }
    @NonNull
    @Override
    public BookmarkAdapter.itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark, parent, false);
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
//                    BookmarkVO member =new BookmarkVO();
//                    bk_members.add(member);
//                }
//            });

        }

        public void onBind(BookmarkVO member){

        }
    }
}

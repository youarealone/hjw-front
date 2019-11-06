package com.example.hjw_front.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.Fragment;

import com.example.hjw_front.R;
import com.example.hjw_front.adapters.BookmarkAdapter;
import com.example.hjw_front.vo.BookmarkVO;

import java.util.LinkedList;
import java.util.List;

public class BookmarkFragment extends Fragment {
    private RecyclerView bk_view;
    private BookmarkAdapter bk_adapter;
    private List<BookmarkVO> bk_memberList = new LinkedList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BookmarkVO member = new BookmarkVO();

        View view = inflater.inflate(R.layout.bookmark_fragment, container, false);

        bk_view = view.findViewById(R.id.bk_view);
        init_bk();
//        bk_memberList.add(member);
//        bk_adapter.notifyDataSetChanged();
        return view;

    }

    private void init_bk() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        bk_view.setLayoutManager(linearLayoutManager);
        bk_view.hasFixedSize();
        bk_adapter = new BookmarkAdapter();
        bk_view.setAdapter(bk_adapter);

    }

}

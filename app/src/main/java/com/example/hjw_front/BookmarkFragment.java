package com.example.hjw_front;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

public class BookmarkFragment extends Fragment {
    private RecyclerView bk_view;
    private BookmarkAdapter bk_adapter;
    private List<BookmarkMember> bk_memberList = new LinkedList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BookmarkMember member = new BookmarkMember();

        View view = inflater.inflate(R.layout.bookmark_fragment, container, false);

        bk_view = view.findViewById(R.id.bk_view);
        init_bk();

        bk_memberList.add(member);
        bk_adapter.notifyDataSetChanged();
        return view;

    }

    private void init_bk() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        bk_view.setLayoutManager(linearLayoutManager);
        bk_view.hasFixedSize();
        bk_adapter = new BookmarkAdapter(bk_memberList);
        bk_view.setAdapter(bk_adapter);

    }

}

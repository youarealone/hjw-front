package com.example.hjw_front;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hjw_front.utils.FragmentChanger;

import java.util.List;

public class ContractFragment extends Fragment {

    private FloatingActionButton fab_contract;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        fab_contract = view.findViewById(R.id.fab_contract);







        return inflater.inflate(R.layout.fragment_contract, container, false);






    }
}
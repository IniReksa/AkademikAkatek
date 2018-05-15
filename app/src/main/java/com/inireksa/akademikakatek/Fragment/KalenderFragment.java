package com.inireksa.akademikakatek.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inireksa.akademikakatek.Adapter.RvKalender;
import com.inireksa.akademikakatek.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class KalenderFragment extends Fragment {

    private RecyclerView rvKalender;
    private RecyclerView.Adapter adapter;


    public KalenderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kalender, container, false);
        rvKalender = (RecyclerView) v.findViewById(R.id.rvKalender);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvKalender.setLayoutManager(layoutManager);
        adapter = new RvKalender();
        rvKalender.setAdapter(adapter);
        return v;
    }

}

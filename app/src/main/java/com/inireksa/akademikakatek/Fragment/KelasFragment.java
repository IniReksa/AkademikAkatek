package com.inireksa.akademikakatek.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inireksa.akademikakatek.Adapter.RvKlsFragment;
import com.inireksa.akademikakatek.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class KelasFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    public String ActiveFragment = "";


    public KelasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kelas, container, false);
        ActiveFragment = "KELAS";
        recyclerView = (RecyclerView) v.findViewById(R.id.rvKelasFragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RvKlsFragment();
        recyclerView.setAdapter(adapter);
        return v;
    }

}

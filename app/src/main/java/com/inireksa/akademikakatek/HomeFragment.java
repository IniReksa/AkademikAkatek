package com.inireksa.akademikakatek;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.inireksa.akademikakatek.Adapter.RvInfoMain;
import com.inireksa.akademikakatek.Adapter.RvJadwalMain;
import com.inireksa.akademikakatek.AdapterMain.GridAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView, rvKelasMain, rvInfo;
    private RecyclerView.Adapter adapter, adapterJadwal, adapterInfo;
    private List<Mahasiswa> listMhs;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rvKelas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GridAdapter();
        recyclerView.setAdapter(adapter);

        rvKelasMain = (RecyclerView) v.findViewById(R.id.rvJadwal);
        LinearLayoutManager layoutManagerJadwal = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvKelasMain.setLayoutManager(layoutManagerJadwal);
        adapterJadwal= new RvJadwalMain();
        rvKelasMain.setAdapter(adapterJadwal);

        rvInfo = (RecyclerView) v.findViewById(R.id.rvInfo);
        LinearLayoutManager layoutManagerinfo = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvInfo.setLayoutManager(layoutManagerinfo);
        adapterInfo = new RvInfoMain();
        rvInfo.setAdapter(adapterInfo);

        ImageView imageView = (ImageView) v.findViewById(R.id.btnListJadwal);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Ini Button List Jadwal", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

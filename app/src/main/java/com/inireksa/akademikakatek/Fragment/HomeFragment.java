package com.inireksa.akademikakatek.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inireksa.akademikakatek.Adapter.RvInfoMain;
import com.inireksa.akademikakatek.Adapter.RvJadwalMain;
import com.inireksa.akademikakatek.Adapter.RvKlsMain;
import com.inireksa.akademikakatek.Model.Mahasiswa;
import com.inireksa.akademikakatek.Model.Value;
import com.inireksa.akademikakatek.R;
import com.inireksa.akademikakatek.SharedPref;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView, rvJadwalMain, rvInfo;
    private RecyclerView.Adapter adapter, adapterJadwal, adapterInfo;
    FragmentTransaction fragmentTransaction;
    public String ActiveFragment = "";
    private TextView npmHome, namaHome;
    private List<Mahasiswa> mahasiswas = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ActiveFragment = "HOME";

        SharedPref sharedPref = new SharedPref(getContext());

        npmHome = v.findViewById(R.id.npmHome);
        namaHome = v.findViewById(R.id.namaHome);

        namaHome.setText(sharedPref.getNama());
        npmHome.setText(Integer.toString(sharedPref.getNpm()));

        Log.d("Data Masuk", String.valueOf(namaHome));
        recyclerView = v.findViewById(R.id.rvKelas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RvKlsMain(getContext(), mahasiswas);
        recyclerView.setAdapter(adapter);

        rvJadwalMain = v.findViewById(R.id.rvJadwal);
        LinearLayoutManager layoutManagerJadwal = new LinearLayoutManager(getContext());
        rvJadwalMain.setLayoutManager(layoutManagerJadwal);
        adapterJadwal= new RvJadwalMain();
        rvJadwalMain.setAdapter(adapterJadwal);

        rvInfo = v.findViewById(R.id.rvInfo);
        LinearLayoutManager layoutManagerinfo = new LinearLayoutManager(getContext());
        rvInfo.setLayoutManager(layoutManagerinfo);
        adapterInfo = new RvInfoMain();
        rvInfo.setAdapter(adapterInfo);

        ImageView btnlistjadwal = v.findViewById(R.id.btnListJadwal);
        ImageView btnlistinfo = v.findViewById(R.id.btnListInfo);
        btnlistjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JadwalFragment jadwalFragment = new JadwalFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, jadwalFragment);
                fragmentTransaction.commit();
                ActiveFragment = "JADWAL";
            }
        });
        btnlistinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoFragment infoFragment = new InfoFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, infoFragment);
                fragmentTransaction.commit();
                ActiveFragment = "INFO";
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child!= null && gestureDetector.onTouchEvent(e)) {
                    KelasFragment kelasFragment = new KelasFragment();
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout, kelasFragment);
                    fragmentTransaction.commit();
                    ActiveFragment = "KELAS";
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

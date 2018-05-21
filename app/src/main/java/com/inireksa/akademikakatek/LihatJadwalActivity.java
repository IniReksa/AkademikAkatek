package com.inireksa.akademikakatek;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.inireksa.akademikakatek.Adapter.RvJadwalMain;
import com.inireksa.akademikakatek.Fragment.JadwalFragment;

public class LihatJadwalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jadwal);

        recyclerView = (RecyclerView) findViewById(R.id.rvJadwalActivity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RvJadwalMain();
        recyclerView.setAdapter(adapter);
    }
}

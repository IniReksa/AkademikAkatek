package com.inireksa.akademikakatek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Adapter.RvJadwalMain;
import com.inireksa.akademikakatek.Model.Jadwal;
import com.inireksa.akademikakatek.Model.JadwalResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LihatJadwalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ProgressBar progressBar;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jadwal);

        sharedPref = new SharedPref(this);

        progressBar = findViewById(R.id.progresJadwalAdmin);
        recyclerView = findViewById(R.id.rvJadwalActivity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ambildatajadwal();
    }

    private void ambildatajadwal() {
        String kelas = sharedPref.getKelas();
        String jurusan = sharedPref.getJurusan();
        String angkatan = sharedPref.getAngkatan();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<JadwalResponse> call = api.jadwal(kelas, jurusan, angkatan);
        call.enqueue(new Callback<JadwalResponse>() {
            @Override
            public void onResponse(Call<JadwalResponse> call, Response<JadwalResponse> response) {

                String error = response.body().Error;
                String message = response.body().Message;

                if (error.equals("0")){
                    List<Jadwal> jadwals = response.body().jadwal;
                    adapter = new RvJadwalMain(LihatJadwalActivity.this, jadwals);
                    recyclerView.setAdapter(adapter);
                } if (error.equals("1")){
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JadwalResponse> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

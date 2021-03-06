package com.inireksa.akademikakatek.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Adapter.RvJadwalMain;
import com.inireksa.akademikakatek.Model.Jadwal;
import com.inireksa.akademikakatek.Model.JadwalResponse;
import com.inireksa.akademikakatek.R;
import com.inireksa.akademikakatek.SharedPref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ProgressBar progressBar;

    SharedPref sharedPref;

    public JadwalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jadwal, container, false);

        sharedPref = new SharedPref(getContext());

        progressBar = v.findViewById(R.id.progresJadwalFragment);
        recyclerView = v.findViewById(R.id.rvJadwalFragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ambildatajadwal();
        return v;
    }

    private void ambildatajadwal() {
        String kelas = sharedPref.getKelas();
        String jurusan = sharedPref.getJurusan();
        String angkatan = sharedPref.getAngkatan();
        String hariIni = "";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<JadwalResponse> call = api.jadwal(kelas, jurusan, angkatan, hariIni);
        call.enqueue(new Callback<JadwalResponse>() {
            @Override
            public void onResponse(Call<JadwalResponse> call, Response<JadwalResponse> response) {

                String error = response.body().Error;
                String message = response.body().Message;
                List<Jadwal> jadwals = response.body().jadwal;

                if (jadwals != null) {
                    if (error.equals("0")) {
                        adapter = new RvJadwalMain(getContext(), jadwals);
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);
                    }
                    if (error.equals("1")) {
//                        progressBar.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Belum Ada Jadwal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JadwalResponse> call, Throwable t) {
//                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

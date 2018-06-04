package com.inireksa.akademikakatek.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Adapter.RvKlsFragment;
import com.inireksa.akademikakatek.Model.MahasiswaResponse;
import com.inireksa.akademikakatek.Model.Mahasiswa;
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
public class KelasFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    SharedPref sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kelas, container, false);
        sharedPref = new SharedPref(getContext());

        recyclerView = v.findViewById(R.id.rvKelasFragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ambildatasekelas();
        return v;
    }

    private void ambildatasekelas() {
        String kelas = sharedPref.getKelas();
        String jurusan = sharedPref.getJurusan();
        String angkatan = sharedPref.getAngkatan();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<MahasiswaResponse> call = api.sekelas(kelas, jurusan, angkatan);
        call.enqueue(new Callback<MahasiswaResponse>() {
            @Override
            public void onResponse(Call<MahasiswaResponse> call, Response<MahasiswaResponse> response) {

                String error = response.body().Error;
                String Message = response.body().Message;
                List<Mahasiswa> mahasiswas = response.body().mahasiswa;

                if (mahasiswas != null) {
                    if (error.equals("0")) {
                        adapter = new RvKlsFragment(getContext(), mahasiswas);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    if (error.equals("1")) {
                        Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Tidak Ada Mahasiswa Lain", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MahasiswaResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

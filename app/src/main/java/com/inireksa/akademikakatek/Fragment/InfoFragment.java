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
import com.inireksa.akademikakatek.Adapter.RvInfoMain;
import com.inireksa.akademikakatek.Model.Info;
import com.inireksa.akademikakatek.Model.InfoResponse;
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
public class InfoFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapterInfoFragment;
    public String ActiveFragment = "";
    SharedPref sharedPref;
    ProgressBar progressBar;


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);
        ActiveFragment = "INFO";

        sharedPref = new SharedPref(getContext());

        progressBar = v.findViewById(R.id.progresInfoFragment);
        recyclerView = v.findViewById(R.id.rvInfoFragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ambildatainfo();
        return v;
    }

    private void ambildatainfo() {
        String kelas = sharedPref.getKelas();
        String jurusan = sharedPref.getJurusan();
        String angkatan = sharedPref.getAngkatan();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<InfoResponse> call = api.info(kelas, jurusan, angkatan);
        call.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {

                String error = response.body().Error;
                String message = response.body().Message;

                if (error.equals("0")){
                    List<Info> infos = response.body().info;
                    adapterInfoFragment = new RvInfoMain(getContext(), infos);
                    recyclerView.setAdapter(adapterInfoFragment);

                } if (error.equals("1")){
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

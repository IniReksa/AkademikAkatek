package com.inireksa.akademikakatek.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Adapter.RvKalender;
import com.inireksa.akademikakatek.Model.Kalender;
import com.inireksa.akademikakatek.Model.KalenderResponse;
import com.inireksa.akademikakatek.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class KalenderFragment extends Fragment {

    private RecyclerView rvKalender;
    private RecyclerView.Adapter adapter;
    private ProgressBar progressBar;
    public String ActiveFragment = "";


    public KalenderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActiveFragment = "KALENDER";
        View v = inflater.inflate(R.layout.fragment_kalender, container, false);
        rvKalender = (RecyclerView) v.findViewById(R.id.rvKalender);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvKalender.setLayoutManager(layoutManager);
        ambildatakalender();
        return v;
    }

    private void ambildatakalender() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<KalenderResponse> call = api.ambilkalender();
        call.enqueue(new Callback<KalenderResponse>() {
            @Override
            public void onResponse(Call<KalenderResponse> call, Response<KalenderResponse> response) {
                String error = response.body().Error;
                String message = response.body().Message;
                List<Kalender> kalenders = response.body().kalender;

                if (error.equals("0")){
                    if (kalenders != null) {
                        adapter = new RvKalender(getContext(), kalenders);
                        rvKalender.setAdapter(adapter);
                    } else {
                        Log.d("Kalender", "data kosong");
                    }
                } if (error.equals("1")){
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KalenderResponse> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

}

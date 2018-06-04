package com.inireksa.akademikakatek.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Adapter.RvNilai;
import com.inireksa.akademikakatek.Model.Nilai;
import com.inireksa.akademikakatek.Model.NilaiResponse;
import com.inireksa.akademikakatek.Model.Semester;
import com.inireksa.akademikakatek.R;
import com.inireksa.akademikakatek.SharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NilaiFragment extends Fragment {

    private RecyclerView rvNilai;
    private RecyclerView.Adapter adapterRv;
    private Dialog mydialog;
    public String pilihsemester;
    private Spinner spinner;
    private SharedPref sharedPref;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nilai, container, false);

        sharedPref = new SharedPref(getContext());

        progressBar = v.findViewById(R.id.progresNilaiFragment);
        spinner = v.findViewById(R.id.spSemester);
        rvNilai = v.findViewById(R.id.rvNilai);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvNilai.setLayoutManager(layoutManager);
        loadDataSemester();

        return v;
    }

    private void loadDataSemester() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<List<Semester>> call = api.ambilsemester();
        call.enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                List<Semester> dataSemester = response.body();
                Log.d("Semester", String.valueOf(dataSemester));

                if (dataSemester != null) {
                    if (response.isSuccessful()) {
                        List<String> listsemester = new ArrayList<String>();
                        for (int i = 0; i < dataSemester.size(); i++) {
                            listsemester.add(dataSemester.get(i).Semester);
                        }
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_item, listsemester);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                pilihsemester = adapter.getItem(i).toString();

                                ambilNilai();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    } else {
                        Toast.makeText(getContext(), "Gagal mengambil angkatan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Tidak Ada data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {
                Toast.makeText(getContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ambilNilai() {
        int npm = sharedPref.getNpm();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<NilaiResponse> call = api.ambilnilai(String.valueOf(npm), pilihsemester);
        call.enqueue(new Callback<NilaiResponse>() {
            @Override
            public void onResponse(Call<NilaiResponse> call, Response<NilaiResponse> response) {
                String error = response.body().Error;
                String message = response.body().Message;
                List<Nilai> nilais = response.body().nilai;

                if (nilais != null) {
                    if (error.equals("0")) {
                        Log.d("matkul dari nilai ", "= " + nilais);
                        adapterRv = new RvNilai(getContext(), nilais);
                        adapterRv.notifyDataSetChanged();
                        rvNilai.setAdapter(adapterRv);
                        progressBar.setVisibility(View.GONE);
                    }
                    if (error.equals("1")) {
//                        progressBar.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Tidak Ada Nilai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NilaiResponse> call, Throwable t) {
//                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

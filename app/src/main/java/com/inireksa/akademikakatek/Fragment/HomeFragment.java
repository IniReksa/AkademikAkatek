package com.inireksa.akademikakatek.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Adapter.RvInfoMain;
import com.inireksa.akademikakatek.Adapter.RvJadwalMain;
import com.inireksa.akademikakatek.Adapter.RvKlsMain;
import com.inireksa.akademikakatek.Model.Info;
import com.inireksa.akademikakatek.Model.InfoResponse;
import com.inireksa.akademikakatek.Model.Jadwal;
import com.inireksa.akademikakatek.Model.JadwalResponse;
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
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView, rvJadwalMain, rvInfo;
    private RecyclerView.Adapter adapter, adapterJadwal, adapterInfo;
    FragmentTransaction fragmentTransaction;
//    public String ActiveFragment = "";
    private TextView npmHome, namaHome;
    private ImageView imgMhs;
    ProgressBar progressKelas, progressJadwal, progressInfo;

    private SharedPref sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

//        ActiveFragment = "HOME";

        sharedPref = new SharedPref(getContext());

        imgMhs = v.findViewById(R.id.img_mhs);
        npmHome = v.findViewById(R.id.npmHome);
        namaHome = v.findViewById(R.id.namaHome);
        Glide.with(getContext())
                .load(sharedPref.getFotoMhs())
                .apply(RequestOptions.circleCropTransform())
                .into(imgMhs);
        namaHome.setText(sharedPref.getNama());
        npmHome.setText(Integer.toString(sharedPref.getNpm()));

        progressKelas = v.findViewById(R.id.progresKlsMain);
        recyclerView = v.findViewById(R.id.rvKelas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ambildataSekelas();

        progressJadwal = v.findViewById(R.id.progresJadwalMain);
        rvJadwalMain = v.findViewById(R.id.rvJadwal);
        LinearLayoutManager layoutManagerJadwal = new LinearLayoutManager(getContext());
        rvJadwalMain.setLayoutManager(layoutManagerJadwal);
        ambildatajadwal();

        progressInfo = v.findViewById(R.id.progresInfoMain);
        rvInfo = v.findViewById(R.id.rvInfo);
        LinearLayoutManager layoutManagerinfo = new LinearLayoutManager(getContext());
        rvInfo.setLayoutManager(layoutManagerinfo);
        ambildatainfo();

        ImageView btnlistjadwal = v.findViewById(R.id.btnListJadwal);
        ImageView btnlistinfo = v.findViewById(R.id.btnListInfo);
        btnlistjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JadwalFragment jadwalFragment = new JadwalFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, jadwalFragment);
                fragmentTransaction.commit();
//                ActiveFragment = "JADWAL";
            }
        });
        btnlistinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoFragment infoFragment = new InfoFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, infoFragment);
                fragmentTransaction.commit();
//                ActiveFragment = "INFO";
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
//                    ActiveFragment = "KELAS";
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

    private void ambildataSekelas() {
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
                        progressKelas.setVisibility(View.GONE);
                        adapter = new RvKlsMain(getContext(), mahasiswas);
                        recyclerView.setAdapter(adapter);
                    }
                    if (error.equals("1")) {
                        //                    progressKelas.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Tidak Ada Mahasiswa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MahasiswaResponse> call, Throwable t) {
//                progressKelas.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
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
                List<Jadwal> jadwals = response.body().jadwal;

                if (jadwals != null) {
                    if (error.equals("0")) {
                        progressJadwal.setVisibility(View.GONE);
                        adapterJadwal = new RvJadwalMain(getContext(), jadwals);
                        rvJadwalMain.setAdapter(adapterJadwal);
                    }
                    if (error.equals("1")) {
//                    progressJadwal.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Tidak Ada Jadwal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JadwalResponse> call, Throwable t) {
//                progressJadwal.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
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
        Call<InfoResponse> call = api.infomain(kelas, jurusan, angkatan);
        call.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {

                    String error = response.body().Error;
                    String message = response.body().Message;
                     List<Info> infos = response.body().info;

                     if (infos != null) {
                         if (error.equals("0")) {
                             progressInfo.setVisibility(View.GONE);
                             adapterInfo = new RvInfoMain(getContext(), infos);
                             rvInfo.setAdapter(adapterInfo);
                         }
                         if (error.equals("1")) {
//                    progressInfo.setVisibility(View.VISIBLE);
                             Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                         }
                     } else {
                         Toast.makeText(getContext(), "Tidak Ada Info", Toast.LENGTH_SHORT).show();
                     }
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
//                progressInfo.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

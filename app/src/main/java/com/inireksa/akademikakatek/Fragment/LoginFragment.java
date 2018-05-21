package com.inireksa.akademikakatek.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Main2Activity;
import com.inireksa.akademikakatek.Model.Value;
import com.inireksa.akademikakatek.R;
import com.inireksa.akademikakatek.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IniReksa on 4/26/2018.
 */

public class
LoginFragment extends Fragment {

    private TextInputEditText eTNama, eTNpm;
    private Button btnLogin;
    private ProgressBar prograsBar;
    public static final String URL = "http://inireksa.000webhostapp.com/akademik/";

    private SharedPref sharedPref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login, container, false);

        eTNama = rootView.findViewById(R.id.user);
        eTNpm = rootView.findViewById(R.id.npm);
        btnLogin = rootView.findViewById(R.id.btnLogin);
        prograsBar = rootView.findViewById(R.id.prograsbar);

        sharedPref = new SharedPref(getContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setVisibility(View.INVISIBLE);
                prograsBar.setVisibility(View.VISIBLE);
                loginAct();
            }
        });
        return rootView;
    }

    private void loginAct() {
        String nama = eTNama.getText().toString();
        String npm = eTNpm.getText().toString();

        if (TextUtils.isEmpty(nama)){
            eTNama.setError("NamaMhs Tidak Boleh Kosong");
            btnLogin.setVisibility(View.VISIBLE);
            prograsBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextUtils.isEmpty(npm)){
            eTNpm.setError("Npm Tidak Boleh Kosong");
            btnLogin.setVisibility(View.VISIBLE);
            prograsBar.setVisibility(View.INVISIBLE);
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<Value> call = api.login(nama, npm);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String error, Message, NamaMhs, Kelas, Jurusan, Angkatan, Alamat, TglLahir, JenisKelamin, FotoMhs, CreatedBy, CreatedAt, UpdateBy, UpdateAt;
                int Npm, NoTlp;

                error = response.body().Error;
                Message = response.body().Message;
                NamaMhs = response.body().mahasiswa.get(0).NamaMhs;
                Npm = response.body().mahasiswa.get(0).Npm;
                Kelas = response.body().mahasiswa.get(0).Kelas;
                Jurusan = response.body().mahasiswa.get(0).Jurusan;
                Angkatan = response.body().mahasiswa.get(0).Angkatan;
                Alamat = response.body().mahasiswa.get(0).Alamat;
                TglLahir = response.body().mahasiswa.get(0).TglLahir;
                JenisKelamin = response.body().mahasiswa.get(0).JenisKelamin;
                NoTlp = response.body().mahasiswa.get(0).NoTlp;
                FotoMhs = response.body().mahasiswa.get(0).FotoMhs;
                CreatedBy = response.body().mahasiswa.get(0).CreatedBy;
                CreatedAt = response.body().mahasiswa.get(0).CreatedAt;
                UpdateAt = response.body().mahasiswa.get(0).UpdatedAt;
                UpdateBy = response.body().mahasiswa.get(0).UpdatedBy;

                if (error.equals("0")){
                    Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
                    prograsBar.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    sharedPref.saveBoolean(SharedPref.SP_SUDAH_LOGIN, true);

                    simpanDataMahasiswa(NamaMhs, Npm, Kelas, Jurusan, Angkatan, Alamat, TglLahir, JenisKelamin, NoTlp, FotoMhs, CreatedBy, CreatedAt, UpdateBy, UpdateAt);

                    Intent intent = new Intent(getActivity(), Main2Activity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();

                } if (error.equals("1")) {
                    prograsBar.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                prograsBar.setVisibility(View.INVISIBLE);
                btnLogin.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Jaringan Error", Toast.LENGTH_SHORT).show();
                t.getStackTrace();
            }
        });
    }

    private void simpanDataMahasiswa(String namaMhs, int npm, String kelas, String jurusan, String angkatan, String alamat, String tgl_lahir, String jenis_kelamin,
                                     int notlp, String fotomhs, String createdBy, String createdAt, String updateBy, String updateAt) {
        sharedPref.setNama(namaMhs);
        sharedPref.setNpm(npm);
        sharedPref.setKelas(kelas);
        sharedPref.setJurusan(jurusan);
        sharedPref.setAngkatan(angkatan);
        sharedPref.setAlamat(alamat);
        sharedPref.setTglLahir(tgl_lahir);
        sharedPref.setJenisKelamin(jenis_kelamin);
        sharedPref.setNoTlp(notlp);
        sharedPref.setFotoMhs(fotomhs);
        sharedPref.setCreatedBy(createdBy);
        sharedPref.setCreatedAt(createdAt);
        sharedPref.setUpdateAt(updateAt);
        sharedPref.setUpdateBy(updateBy);
    }
}

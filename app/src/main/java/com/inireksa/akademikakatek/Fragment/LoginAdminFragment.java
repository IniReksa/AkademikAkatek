package com.inireksa.akademikakatek.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.AdminActivity;
import com.inireksa.akademikakatek.Model.LoginAdminResponse;
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

public class LoginAdminFragment extends Fragment {

    TextView etAdmin, etPass;
    Button btnLoginAdmin;
    ProgressBar progressBar;

    private SharedPref sharedPref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_admin, container, false);

        etAdmin = rootView.findViewById(R.id.useradmin);
        etPass = rootView.findViewById(R.id.passadmin);
        btnLoginAdmin = rootView.findViewById(R.id.btnadmin);
        progressBar = rootView.findViewById(R.id.prograsbaradmin);
        sharedPref = new SharedPref(getContext());

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLoginAdmin.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                LoginAdmin();
            }
        });
        return rootView;
    }

    private void LoginAdmin() {
        String nama = etAdmin.getText().toString();
        String pass = etPass.getText().toString();

        if (TextUtils.isEmpty(nama)){
            etAdmin.setError("Nama Admin Tidak Boleh Kosong");
            btnLoginAdmin.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextUtils.isEmpty(pass)){
            etPass.setError("Password Tidak Boleh Kosong");
            btnLoginAdmin.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<LoginAdminResponse> call = api.loginadmin(nama, pass);
        call.enqueue(new Callback<LoginAdminResponse>() {
            @Override
            public void onResponse(Call<LoginAdminResponse> call, Response<LoginAdminResponse> response) {
                String Error = response.body().Error;
                String Message = response.body().Message;
                String NamaAdmin = response.body().NamaAdmin;

                if (Error.equals("0")){
                    progressBar.setVisibility(View.INVISIBLE);
                    btnLoginAdmin.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), Message , Toast.LENGTH_SHORT).show();

                    sharedPref.setNamaAdmin(NamaAdmin);
                    sharedPref.saveBooleanAdmin(sharedPref.SP_ADMIN_SUDAH_LOGIN, true);

                    Intent intent = new Intent(getActivity(), AdminActivity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    btnLoginAdmin.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginAdminResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                btnLoginAdmin.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

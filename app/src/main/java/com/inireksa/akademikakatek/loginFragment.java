package com.inireksa.akademikakatek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IniReksa on 4/26/2018.
 */

public class loginFragment extends Fragment {

    private TextInputEditText eTNama, eTNpm;
    private Button btnLogin;
    private ProgressBar prograsBar;
    public static final String URL = "http://inireksa.000webhostapp.com/akademik/";

    private Session session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login, container, false);

        eTNama = (TextInputEditText) rootView.findViewById(R.id.user);
        eTNpm = (TextInputEditText) rootView.findViewById(R.id.npm);
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        prograsBar = (ProgressBar) rootView.findViewById(R.id.prograsbar);

        session = new Session(getActivity());
        if (session.loggedin()){
            getActivity().startActivity(new Intent(getActivity(), Main2Activity.class));
            getActivity().finish();
        }
        
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
            eTNama.setError("Nama Tidak Boleh Kosong");
            btnLogin.setVisibility(View.VISIBLE);
            prograsBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextUtils.isEmpty(npm)){
            eTNpm.setError("NPM Tidak Boleh Kosong");
            btnLogin.setVisibility(View.VISIBLE);
            prograsBar.setVisibility(View.INVISIBLE);
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<Value> call = api.login(nama, npm);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String error = response.body().getError();
                String message = response.body().getMessage();

                if (error.equals("0")){
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    prograsBar.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);

                    session = new Session(getActivity().getApplicationContext());
                    session.setLoggedin(true);

                    Intent intent = new Intent(getActivity(), Main2Activity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();

                } if (error.equals("1")) {
                    prograsBar.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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
}

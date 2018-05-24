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

import com.inireksa.akademikakatek.AdminActivity;
import com.inireksa.akademikakatek.R;
import com.inireksa.akademikakatek.SharedPref;

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
//
        sharedPref = new SharedPref(getContext());
//        if (sharedPref.getAdminSudahLogin()){
//            startActivity(new Intent(getContext(), AdminActivity.class));
//            getActivity().finish();
//        }
//        if (sharedPref.getSudahLogin()) {
//            startActivity(new Intent(getContext(), MainActivity.class));
//            getActivity().finish();
//        }

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
            etAdmin.setError("NamaMhs Tidak Boleh Kosong");
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

        if(nama.equals("admin") && pass.equals("admin")){
            progressBar.setVisibility(View.INVISIBLE);
            btnLoginAdmin.setVisibility(View.VISIBLE);
//
            sharedPref.saveBooleanAdmin(sharedPref.SP_ADMIN_SUDAH_LOGIN, true);

            Intent intent = new Intent(getActivity(), AdminActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        } else {
            Toast.makeText(getActivity(), "NamaMhs dan Password salah", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            btnLoginAdmin.setVisibility(View.VISIBLE);
        }
    }
}

package com.inireksa.akademikakatek;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Model.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IniReksa on 5/24/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyfirebaseIdService";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        //calling the method store token and passing token
        storeToken(refreshedToken);
    }

    private void storeToken(String token) {
        //we will save the token in sharedpreferences later
        SharedPref sharedPref = new SharedPref(getApplicationContext());
        sharedPref.setDeviceToken(token);
        Integer npm = sharedPref.getNpm();

        if (npm != null) {
            if (token != null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                InterfaceAPI api = retrofit.create(InterfaceAPI.class);
                Call<ServerResponse> call = api.tambahtoken(String.valueOf(npm), token);
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        Log.d("Token", response.body().Message);
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {

                    }
                });
            } else {
                Log.d("Token", "Token Belum dapat");
            }
        } else {
            Log.e("MyfirebaseId", "NPM blum di ambil");
        }
    }
}

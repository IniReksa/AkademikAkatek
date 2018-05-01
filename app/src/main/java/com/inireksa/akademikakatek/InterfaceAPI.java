package com.inireksa.akademikakatek;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by IniReksa on 4/27/2018.
 */

public interface InterfaceAPI {

    @FormUrlEncoded
    @POST("login.php")
    Call<Value> login(@Field("nama") String nama,
                      @Field("npm") String npm);
}

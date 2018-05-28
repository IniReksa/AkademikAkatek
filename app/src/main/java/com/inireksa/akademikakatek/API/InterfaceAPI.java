package com.inireksa.akademikakatek.API;
import com.inireksa.akademikakatek.Model.InfoResponse;
import com.inireksa.akademikakatek.Model.JadwalResponse;
import com.inireksa.akademikakatek.Model.LoginAdminResponse;
import com.inireksa.akademikakatek.Model.MahasiswaResponse;
import com.inireksa.akademikakatek.Model.LoginResponse;
import com.inireksa.akademikakatek.Model.Angkatan;
import com.inireksa.akademikakatek.Model.ServerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by IniReksa on 4/27/2018.
 */

public interface InterfaceAPI {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("nama") String nama,
                                       @Field("npm") String npm);

    @FormUrlEncoded
    @POST("loginadmin")
    Call<LoginAdminResponse> loginadmin(@Field("admin") String nama,
                                        @Field("password") String npm);

    @FormUrlEncoded
    @POST("sekelas")
    Call<MahasiswaResponse> sekelas(@Field("kelas") String kelas,
                                    @Field("jurusan") String jurusan,
                                    @Field("angkatan") String angkatan);

    @FormUrlEncoded
    @POST("jadwal")
    Call<JadwalResponse> jadwal (@Field("kelas") String kelas,
                                 @Field("jurusan") String jurusan,
                                 @Field("angkatan") String angkatan);

    @FormUrlEncoded
    @POST("info")
    Call<InfoResponse> info (@Field("kelas") String kelas,
                             @Field("jurusan") String jurusan,
                             @Field("angkatan") String angkatan);

    @FormUrlEncoded
    @POST("infomain")
    Call<InfoResponse> infomain (@Field("kelas") String kelas,
                                 @Field("jurusan") String jurusan,
                                 @Field("angkatan") String angkatan);

    @FormUrlEncoded
    @POST("kiriminfo")
    Call<ServerResponse> kiriminfo (@Field("angkatan") String angkatan,
                                   @Field("title") String title,
                                   @Field("message") String message);

    @FormUrlEncoded
    @POST("kiriminfoperkelas")
    Call<ServerResponse> kiriminfoperkelas (@Field("angkatan") String angkatan,
                                    @Field("title") String title,
                                    @Field("message") String message);

    @FormUrlEncoded
    @POST("tambahtoken")
    Call<ServerResponse> tambahtoken (@Field("npm") String npm,
                                      @Field("token") String token);

    @GET("ambilangkatan")
    Call<List<Angkatan>> ambilangkatan();
}

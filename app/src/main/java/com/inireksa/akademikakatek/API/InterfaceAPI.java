package com.inireksa.akademikakatek.API;
import com.inireksa.akademikakatek.Model.InfoResponse;
import com.inireksa.akademikakatek.Model.JadwalResponse;
import com.inireksa.akademikakatek.Model.KalenderResponse;
import com.inireksa.akademikakatek.Model.LoginAdminResponse;
import com.inireksa.akademikakatek.Model.MahasiswaResponse;
import com.inireksa.akademikakatek.Model.LoginResponse;
import com.inireksa.akademikakatek.Model.SpinnerAngkatan;
import com.inireksa.akademikakatek.Model.NilaiResponse;
import com.inireksa.akademikakatek.Model.Semester;
import com.inireksa.akademikakatek.Model.ServerResponse;
import com.inireksa.akademikakatek.Model.SpinnerDosen;
import com.inireksa.akademikakatek.Model.SpinnerHari;
import com.inireksa.akademikakatek.Model.SpinnerJurusan;
import com.inireksa.akademikakatek.Model.SpinnerKelas;
import com.inireksa.akademikakatek.Model.SpinnerMatkul;
import com.inireksa.akademikakatek.Model.SpinnerRuangan;
import com.inireksa.akademikakatek.Model.SpinnerSesi;

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
                                 @Field("angkatan") String angkatan,
                                 @Field("hariIni") String hariIni);

    @FormUrlEncoded
    @POST("info")
    Call<InfoResponse> info (@Field("angkatan") String angkatan);

    @FormUrlEncoded
    @POST("infomain")
    Call<InfoResponse> infomain (@Field("angkatan") String angkatan);

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

    @GET("ambilkalender")
    Call<KalenderResponse> ambilkalender();

    @GET("ambilsemester")
    Call<List<Semester>> ambilsemester();

    @FormUrlEncoded
    @POST("ambilnilai")
    Call<NilaiResponse> ambilnilai(@Field("npm") String npm,
                                   @Field("semester") String semester);

    @GET("ambilangkatan")
    Call<List<SpinnerAngkatan>> ambilangkatan();

    @GET("ambilkelas")
    Call<List<SpinnerKelas>> ambilkelas();

    @GET("ambiljurusan")
    Call<List<SpinnerJurusan>> ambiljurusan();

    @GET("ambilsemuajadwal")
    Call<JadwalResponse> ambilsemuajadwal();

    @GET("ambildosen")
    Call<List<SpinnerDosen>> ambildosen();

    @FormUrlEncoded
    @POST("ambilmatkul")
    Call<List<SpinnerMatkul>> ambilmatkul(@Field("kelas") String kelas,
                                          @Field("jurusan") String jurusan,
                                          @Field("angkatan") String angkatan);

    @FormUrlEncoded
    @POST("updatejadwal")
    Call<ServerResponse> updatejadwal(@Field("id") String id,
                                      @Field("dosen") String dosen,
                                      @Field("sesi") String sesi,
                                      @Field("hari") String hari,
                                      @Field("ruangan") String ruangan);


}

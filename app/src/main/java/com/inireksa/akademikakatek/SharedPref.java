package com.inireksa.akademikakatek;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by IniReksa on 5/15/2018.
 */

public class SharedPref {

    public static final String SP_AKADEMIK_APP = "spAkademikapp";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";
    public static final String SP_ADMIN_SUDAH_LOGIN = "spAdminSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public SharedPref(Context context){
        sp = context.getSharedPreferences(SP_AKADEMIK_APP, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void saveBoolean(String keySP, boolean value){
        editor.putBoolean(keySP, value);
        editor.commit();
    }

    public void saveBooleanAdmin(String keySP, boolean value){
        editor.putBoolean(keySP, value);
        editor.commit();
    }

    public Boolean getSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public Boolean getAdminSudahLogin(){
        return sp.getBoolean(SP_ADMIN_SUDAH_LOGIN, false);
    }

    public void setNama(String nama){
        editor.putString("nama", nama);
        editor.commit();
    }

    public String getNama(){
        return sp.getString("nama", null);
    }

    public void setNpm(Integer npm){
        editor.putString("npm", String.valueOf(npm));
        editor.commit();
    }

    public Integer getNpm(){
        return Integer.valueOf(sp.getString("npm", null));
    }

    public String getKelas() {
        return sp.getString("kelas", null);
    }

    public void setKelas(String kelas) {
        editor.putString("kelas", kelas);
        editor.commit();
    }

    public String getJurusan() {
        return sp.getString("jurusan", null);
    }

    public void setJurusan(String jurusan) {
        editor.putString("jurusan", jurusan);
        editor.commit();
    }

    public String getAngkatan() {
        return sp.getString("Angkatan", null);
    }

    public void setAngkatan(String angkatan) {
        editor.putString("Angkatan", angkatan);
        editor.commit();
    }

    public String getAlamat() {
        return sp.getString("alamat", null);
    }

    public void setAlamat(String alamat) {
        editor.putString("alamat", alamat);
        editor.commit();;
    }

    public String getTglLahir() {
        return sp.getString("tgllahir", null);
    }

    public void setTglLahir(String tglLahir) {
        editor.putString("tgllahir", tglLahir);
        editor.commit();;
    }

    public String getJenisKelamin() {
        return sp.getString("jeniskelamin", null);
    }

    public void setJenisKelamin(String jenisKelamin) {
        editor.putString("jeniskelamin", jenisKelamin);
        editor.commit();
    }

    public int getNoTlp() {
        return Integer.valueOf(sp.getString("notlp", null));
    }

    public void setNoTlp(int noTlp) {
        editor.putString("notlp", String.valueOf(noTlp));
        editor.commit();
    }

    public String getFotoMhs() {
        return sp.getString("fotomhs", null);
    }

    public void setFotoMhs(String fotoMhs) {
        editor.putString("fotomhs", fotoMhs);
        editor.commit();
    }

    public String getCreatedBy() {
        return sp.getString("createdby", null);
    }

    public void setCreatedBy(String createdBy) {
        editor.putString("createdby", createdBy);
        editor.commit();
    }

    public String getCreatedAt() {
        return sp.getString("createdat", null);
    }

    public void setCreatedAt(String createdAt) {
        editor.putString("createdat", createdAt);
        editor.commit();
    }

    public String getUpdateBy() {
        return sp.getString("updatedby", null);
    }

    public void setUpdateBy(String updateBy) {
        editor.putString("updatedby", updateBy);
        editor.commit();
    }

    public String getUpdateAt() {
        return sp.getString("updatedat", null);
    }

    public void setUpdateAt(String updateAt) {
        editor.putString("updatedat", updateAt);
        editor.commit();
    }

    public String getNamaAdmin() {
        return sp.getString("namaadmin", null);
    }

    public void setNamaAdmin(String namaAdmin) {
        editor.putString("namaadmin", namaAdmin);
        editor.commit();
    }

    public boolean setDeviceToken(String token){
        editor.putString("token", token);
        editor.commit();
        return true;
    }

    public String getDeviceToken(){
        return sp.getString("token", null);
    }
}

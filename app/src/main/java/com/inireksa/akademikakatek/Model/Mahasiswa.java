package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/5/2018.
 */

public class Mahasiswa {


    public int Npm;
    public String NamaMhs;
    public String Kelas;
    public String Jurusan;
    public String Angkatan;
    public String Alamat;
    public String TglLahir;
    public String JenisKelamin;
    public int NoTlp;
    public String FotoMhs;
    public String CreatedBy;
    public String CreatedAt;
    public String UpdatedBy;
    public String UpdatedAt;


    public Mahasiswa(int Npm, String NamaMhs, String kelas, String jurusan, String angkatan,
                     String alamat, String tgl_lahir, String jenis_kelamin, int notlp, String FotoMhs, String createdBy, String createdAt, String updatedBy, String updatedAt) {
        this.Npm = Npm;
        this.NamaMhs = NamaMhs;
        this.Kelas = kelas;
        this.Jurusan = jurusan;
        this.Angkatan = angkatan;
        this.Alamat = alamat;
        this.TglLahir = tgl_lahir;
        this.JenisKelamin = jenis_kelamin;
        this.NoTlp = notlp;
        this.FotoMhs = FotoMhs;
        this.CreatedBy = createdBy;
        this.CreatedAt = createdAt;
        this.UpdatedBy = updatedBy;
        this.UpdatedAt = updatedAt;
    }
}

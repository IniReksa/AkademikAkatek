package com.inireksa.akademikakatek;

/**
 * Created by IniReksa on 5/5/2018.
 */

public class Mahasiswa {

    public String nama, kelas;
    public int Nohp, Npm, img;

    public Mahasiswa() {
    }

    public Mahasiswa(String nama, String kelas, int nohp, int npm) {
        this.nama = nama;
        this.kelas = kelas;
        Nohp = nohp;
        Npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public int getNohp() {
        return Nohp;
    }

    public void setNohp(int nohp) {
        Nohp = nohp;
    }

    public int getNpm() {
        return Npm;
    }

    public void setNpm(int npm) {
        Npm = npm;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}

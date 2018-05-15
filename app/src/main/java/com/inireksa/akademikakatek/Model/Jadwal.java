package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/7/2018.
 */

public class Jadwal {

    public String sesi;
    public String matkul;
    public String dosen;
    public String ruangan;
    public String hari;

    public Jadwal(String sesi, String matkul, String dosen, String ruangan, String hari) {
        this.sesi = sesi;
        this.matkul = matkul;
        this.dosen = dosen;
        this.ruangan = ruangan;
        this.hari = hari;
    }
}

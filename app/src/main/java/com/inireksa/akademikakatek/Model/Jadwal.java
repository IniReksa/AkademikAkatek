package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/7/2018.
 */

public class Jadwal {

    public String Sesi;
    public String Matkul;
    public String Dosen;
    public String Ruangan;
    public String Hari;

    public Jadwal(String sesi, String matkul, String dosen, String ruangan, String hari) {
        this.Sesi = sesi;
        this.Matkul = matkul;
        this.Dosen = dosen;
        this.Ruangan = ruangan;
        this.Hari = hari;
    }
}

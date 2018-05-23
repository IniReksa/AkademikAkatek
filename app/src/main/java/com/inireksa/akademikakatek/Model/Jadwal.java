package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/7/2018.
 */

public class Jadwal {

    public String Sesi;
    public String NamaMk;
    public String NamaDosen;
    public String Ruangan;
    public String Hari;

    public Jadwal(String sesi, String namaMk, String namaDosen, String ruangan, String hari) {
        this.Sesi = sesi;
        this.NamaMk = namaMk;
        this.NamaDosen = namaDosen;
        this.Ruangan = ruangan;
        this.Hari = hari;
    }
}

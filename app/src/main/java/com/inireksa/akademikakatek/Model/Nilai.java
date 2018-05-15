package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/14/2018.
 */

public class Nilai {

    public String matkul;
    public String kehadiran;
    public String tugas;
    public String uts;
    public String uas;
    public String nilaiakhir;

    public Nilai(String matkul, String kehadiran, String tugas, String uts, String uas, String nilaiakhir) {
        this.matkul = matkul;
        this.kehadiran = kehadiran;
        this.tugas = tugas;
        this.uts = uts;
        this.uas = uas;
        this.nilaiakhir = nilaiakhir;
    }
}

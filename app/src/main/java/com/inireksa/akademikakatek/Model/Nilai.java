package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/14/2018.
 */

public class Nilai {

    public String Matkul;
    public String Kehadiran;
    public String Tugas;
    public String UTS;
    public String UAS;
    public String NilaiAkhir;

    public Nilai(String matkul, String kehadiran, String tugas, String uts, String uas, String nilaiakhir) {
        this.Matkul = matkul;
        this.Kehadiran = kehadiran;
        this.Tugas = tugas;
        this.UTS = uts;
        this.UAS = uas;
        this.NilaiAkhir = nilaiakhir;
    }
}

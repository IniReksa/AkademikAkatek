package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/14/2018.
 */

public class Nilai {

    public String NamaMk;
    public String Kehadiran;
    public String Tugas;
    public String Uts;
    public String Uas;
    public String NilaiAkhir;
    public String Semester;

    public Nilai(String NamaMk, String kehadiran, String tugas, String Uts, String Uas, String nilaiAkhir, String semester) {
        this.NamaMk = NamaMk;
        this.Kehadiran = kehadiran;
        this.Tugas = tugas;
        this.Uts = Uts;
        this.Uas = Uas;
        this.NilaiAkhir = nilaiAkhir;
        this.Semester = semester;
    }
}

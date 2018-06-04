package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/7/2018.
 */

public class Jadwal {

    public String IdJadwal;
    public String Jurusan;
    public String Angkatan;
    public String Kelas;
    public String KodeMk;
    public String Sesi;
    public String NamaMk;
    public String Ruangan;
    public String Hari;
    public String Semester;
    public String NamaDosen;
    public String IdDosen;

    public Jadwal(String idJadwal, String jurusan, String angkatan, String kelas, String kodeMk, String sesi,
                  String namaMk, String ruangan, String hari, String semester, String namaDosen, String idDosen) {
        IdJadwal = idJadwal;
        Jurusan = jurusan;
        Angkatan = angkatan;
        Kelas = kelas;
        KodeMk = kodeMk;
        Sesi = sesi;
        NamaMk = namaMk;
        Ruangan = ruangan;
        Hari = hari;
        Semester = semester;
        NamaDosen = namaDosen;
        IdDosen = idDosen;
    }
}

package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/11/2018.
 */

public class Kalender {
    public String Waktu;
    public String Kegiatan;
    public String Keterangan;
    public String CreatedBy;
    public String CreatedAt;
    public String UpdateBy;
    public String UpdateAt;

    public Kalender(String waktu, String kegiatan, String keterangan, String createdBy,
                    String createdAt, String updateBy, String updateAt) {
        Waktu = waktu;
        Kegiatan = kegiatan;
        Keterangan = keterangan;
        CreatedBy = createdBy;
        CreatedAt = createdAt;
        UpdateBy = updateBy;
        UpdateAt = updateAt;
    }
}

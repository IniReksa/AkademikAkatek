package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/9/2018.
 */

public class Info {
    public String Angkatan;
    public String Jurusan;
    public String IsiInfo;
    public String Kelas;
    public String CreatedBy;
    public String CreatedAt;
    public String UpdateBy;
    public String UpdateAt;

    public Info(String angkatan, String jurusan, String isiInfo, String kelas, String createdBy,
                String createdAt, String updateBy, String updateAt) {
        this.Angkatan = angkatan;
        this.Jurusan = jurusan;
        this.IsiInfo = isiInfo;
        this.Kelas = kelas;
        this.CreatedBy = createdBy;
        this.CreatedAt = createdAt;
        this.UpdateBy = updateBy;
        this.UpdateAt = updateAt;
    }
}

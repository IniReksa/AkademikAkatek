package com.inireksa.akademikakatek.Model;

import java.util.List;

/**
 * Created by IniReksa on 4/27/2018.
 */

public class MahasiswaResponse {

    public String Error;
    public String Message;
    public List<Mahasiswa> mahasiswa;

    public MahasiswaResponse(String error, String message, List<Mahasiswa> mahasiswa) {
        this.Error = error;
        this.Message = message;
        this.mahasiswa = mahasiswa;
    }
}

package com.inireksa.akademikakatek.Model;

import java.util.List;

/**
 * Created by IniReksa on 4/27/2018.
 */

public class NilaiResponse {

    public String Error;
    public String Message;
    public List<Nilai> nilai;

    public NilaiResponse(String error, String message, List<Nilai> nilai) {
        this.Error = error;
        this.Message = message;
        this.nilai = nilai;
    }
}

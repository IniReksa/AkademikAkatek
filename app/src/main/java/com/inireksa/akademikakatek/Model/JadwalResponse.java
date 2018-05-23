package com.inireksa.akademikakatek.Model;

import java.util.List;

/**
 * Created by IniReksa on 4/27/2018.
 */

public class JadwalResponse {

    public String Error;
    public String Message;
    public List<Jadwal> jadwal;

    public JadwalResponse(String error, String message, List<Jadwal> jadwal) {
        this.Error = error;
        this.Message = message;
        this.jadwal = jadwal;
    }
}

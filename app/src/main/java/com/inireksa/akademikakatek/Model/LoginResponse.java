package com.inireksa.akademikakatek.Model;

import java.util.List;

/**
 * Created by IniReksa on 4/27/2018.
 */

public class LoginResponse {

    public String Error;
    public String Message;
    public Mahasiswa mahasiswa;

    public LoginResponse(String error, String message, Mahasiswa mahasiswa) {
        this.Error = error;
        this.Message = message;
        this.mahasiswa = mahasiswa;
    }
}

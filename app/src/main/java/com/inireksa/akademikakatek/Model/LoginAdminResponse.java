package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/24/2018.
 */

public class LoginAdminResponse {

    public String Error;
    public String Message;
    public String NamaAdmin;

    public LoginAdminResponse(String error, String message, String namaAdmin) {
        this.Error = error;
        this.Message = message;
        this.NamaAdmin = namaAdmin;
    }
}

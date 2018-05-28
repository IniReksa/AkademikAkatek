package com.inireksa.akademikakatek.Model;

/**
 * Created by IniReksa on 5/25/2018.
 */

public class ServerResponse {
    public String Error;
    public String Message;

    public ServerResponse(String error, String message) {
        this.Error = error;
        this.Message = message;
    }
}

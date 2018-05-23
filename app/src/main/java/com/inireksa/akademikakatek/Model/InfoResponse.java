package com.inireksa.akademikakatek.Model;

import java.util.List;

/**
 * Created by IniReksa on 4/27/2018.
 */

public class InfoResponse {

    public String Error;
    public String Message;
    public List<Info> info;

    public InfoResponse(String error, String message, List<Info> info) {
        this.Error = error;
        this.Message = message;
        this.info = info;
    }
}

package com.inireksa.akademikakatek.Model;

import java.util.List;

/**
 * Created by IniReksa on 4/27/2018.
 */

public class KalenderResponse {

    public String Error;
    public String Message;
    public List<Kalender> kalender;

    public KalenderResponse(String error, String message, List<Kalender> kalender) {
        this.Error = error;
        this.Message = message;
        this.kalender = kalender;
    }
}

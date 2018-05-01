package com.inireksa.akademikakatek;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by IniReksa on 4/30/2018.
 */

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int private_mode = 0;
    private static final String KEYPREF = "Key Profile";

    public Session(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoggedin(boolean loggedin){
        editor.putBoolean("loggedInMode", loggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return sharedPreferences.getBoolean("loggedInMode", false);
    }
}

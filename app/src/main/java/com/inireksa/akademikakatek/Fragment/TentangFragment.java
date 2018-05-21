package com.inireksa.akademikakatek.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inireksa.akademikakatek.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TentangFragment extends Fragment {

    public String ActiveFragment = "";


    public TentangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActiveFragment = "TENTANG";
        View v = inflater.inflate(R.layout.fragment_tentang, container, false);


        return v;
    }

}

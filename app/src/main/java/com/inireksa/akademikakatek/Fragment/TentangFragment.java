package com.inireksa.akademikakatek.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.inireksa.akademikakatek.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TentangFragment extends Fragment {

    String url = "file:///android_asset/TentangKampus.html";
    WebView webView;


    public TentangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tentang, container, false);

        webView = v.findViewById(R.id.webview);
        webView.loadUrl(url);

        return v;
    }
}

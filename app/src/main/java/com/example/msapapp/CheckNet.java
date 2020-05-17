package com.example.msapapp;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckNet extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(haveNetwork()) {
            //available
        }
        else if (!haveNetwork()) {
            //not available
        }
    }

    public boolean haveNetwork() {
        boolean have_WIFI = false;
        boolean have_DATA = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info:networkInfos) {
            if(info.getTypeName().equalsIgnoreCase("WIFI")) {
                if(info.isConnected())
                    have_WIFI=true;
            }
            if(info.getTypeName().equalsIgnoreCase("MOBILE")) {
                if(info.isConnected())
                    have_DATA=true;
            }
        }
        return have_DATA||have_WIFI;
    }
}

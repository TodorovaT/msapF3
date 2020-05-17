package com.example.msapapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//fetches the data from the url page, phase 2

public class fetchData extends AsyncTask<Void, Void, String> {
    String data;
    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("http://192.168.100.2:5000/getjobs/hardware");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while (line!=null) {
                line=bufferedReader.readLine();
                data=data+line;
            }
            return data;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }
}

package com.example.msapapp;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UnparseJSON extends AppCompatActivity {
    class Unparse extends JSONObject {
        public void unp() {
            String data;
            fetchData fdata = new fetchData();
            data = fdata.data;
            try {
                String pingCmd = "ping -s 100 -c 10 " + "10.0.2.2";
                String pingResult = "";
                Runtime r = Runtime.getRuntime();
                Process p = r.exec(pingCmd);
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                    data=(inputLine + "\n\n");
                    pingResult += inputLine;
                    data=pingResult;
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.example.msapapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Build;
import com.example.msapapp.restarter.RestartServiceBroadcastReceiver;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setSontentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RestartServiceBroadcastReceiver.scheduleJob(getApplicationContext());
        }
        else {
            ProcessMainClass bck=new ProcessMainClass();
            bck.launchService(getApplicationContext());
        }
        finish();
    }
}


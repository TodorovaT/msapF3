package com.example.msapapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.util.Log.d;

//repeats the job, every 10 minutes, phase 2

public class Repeater extends AppCompatActivity {
    private static final String TAG = "Repeater";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void startThread (View view) {
        CheckNet net=new CheckNet();
        if(net.haveNetwork()) {
            ExampleRunnable runnable = new ExampleRunnable();
            new Thread(runnable).start();
        }
    }

    class ExampleRunnable implements Runnable {
        @Override
        public void run () {
            int i=1;
            while (i>0) {
                fetchData fetch = new fetchData();
                fetch.doInBackground();
                Log.d(TAG, "StartThread"+i);
                try {
                    Thread.sleep(600000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

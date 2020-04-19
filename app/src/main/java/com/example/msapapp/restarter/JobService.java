package com.example.msapapp.restarter;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.msapapp.Globals;
import com.example.msapapp.ProcessMainClass;
import com.example.msapapp.R;

@RequiresApi(api=Build.VERSION_CODES.LOLLIPOP)

    public class JobService extends android.app.job.JobService {
    private static String TAG=JobService.class.getSimpleName();
    private static RestartServiceBroadcastReceiver restartSensorServiceReceiver;
    private static JobService instance;
    private static JobParameters jobParameters;

    public boolean onStartJob(JobParameters jobParameters) {
        ProcessMainClass bck = new ProcessMainClass();
        bck.launchService(this);
        registerRestarterReceiver();
        instance=this;
        JobService.jobParameters=jobParameters;
        return false;
    }

    private void registerRestarterReceiver() {
        if (restartSensorServiceReceiver==null)
            restartSensorServiceReceiver = new RestartServiceBroadcastReceiver();
        else try {
            unregisterReceiver(restartSensorServiceReceiver);
        }
        catch (Exception e) {

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentFilter filter = new IntentFilter();
                filter.addAction(Globals.RESTART_INTENT);
                try {
                    registerReceiver(restartSensorServiceReceiver,filter);
                }
                catch (Exception e) {
                    try {
                        getApplicationContext().registerReceiver(restartSensorServiceReceiver,filter);
                    }
                    catch (Exception ex) {
                        //empty
                    }
                }
            }
        },1000);
    }
    
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG,"Stopping job");
        Intent broadcastIntent = new Intent(Globals.RESTART_INTENT);
        sendBroadcast(broadcastIntent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                unregisterReceiver(restartSensorServiceReceiver);
            }
        },1000);
        return false;
    }

    public static void stopJob (Context context) {
        if (instance!=null && jobParameters!=null) {
            try {
                instance.unregisterReceiver(restartSensorServiceReceiver);
            }
            catch (Exception e) {

            }
            Log.i(TAG,"Finishing job");
            instance.jobFinished(jobParameters,true);
        }
    }
}

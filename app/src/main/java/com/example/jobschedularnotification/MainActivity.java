package com.example.jobschedularnotification;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    Button btn_schedulejob, btn_canceljob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_schedulejob = (Button) findViewById(R.id.schedulejob);
        btn_canceljob = (Button) findViewById(R.id.canceljob);
        btn_schedulejob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComponentName componentName = new ComponentName(MainActivity.this, ExampleJobService.class);
                JobInfo info = new JobInfo.Builder(123, componentName)
                        .setRequiresCharging(true)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                        .setPersisted(true)
                        .setPeriodic(15 * 60 * 1000)
                        .build();

                JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                int resultcode = jobScheduler.schedule(info);
                if (resultcode == jobScheduler.RESULT_SUCCESS) {
                    Log.d(TAG, "JobScheduled");
                } else {
                    Log.d(TAG, "JobScheduling Failed");
                }
            }
        });
        btn_canceljob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                jobScheduler.cancel(123);
                Log.d(TAG,"Job Cancelled");
            }
        });
    }

}

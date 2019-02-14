package com.example.jobschedularnotification;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExampleJobService extends JobService {
    private static final String TAG = "JobService";
    private boolean jobcancelled = false;


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job Started");
        dobackgroundwork(params);
        return true;
    }

    private void dobackgroundwork(final JobParameters params) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++)
                {
                    Log.d(TAG,"Run"+i);
                    if(jobcancelled)
                    {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG,"Finished");
                jobFinished(params,false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG,"Job Cancellation has been done before starts");
        jobcancelled=true;
        return true;
    }
}

package com.example.android.quakereport.shedulling;

//import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.android.quakereport.EarthquakeActivity;
import com.example.android.quakereport.Networking.Earthquake;
import com.firebase.jobdispatcher.JobService;
public class MyJobSchedule extends JobService{
    private static AsyncTask mBackgroundTask;
    @Override
    public boolean onStartJob(final com.firebase.jobdispatcher.JobParameters job) {
        mBackgroundTask=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context=MyJobSchedule.this;
                //Intent intent =new Intent(MyJobSchedule.this, EarthquakeActivity.class);
                Reminder.execteTask(context,Reminder.job_schedule_string);
                return null;
            }
            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job,false);
            }
        };
        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        if(mBackgroundTask!=null)
            mBackgroundTask.cancel(true);
        return true;
    }

}
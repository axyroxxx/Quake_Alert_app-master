package com.example.android.quakereport.shedulling;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class JobScheduleutil {
    private static final int REMINDER_TIME =1;
    private static final int REMINDER_TIME_SEC=(int)(TimeUnit.MINUTES.toSeconds(REMINDER_TIME));
    private static final int SYN_FLEX_TIME=REMINDER_TIME_SEC;
    private static final String REMINDER_JOB_TAG="hydration_reminder_tag";
    private static boolean isInitialise;

    synchronized public static void sheduleReminder(@NonNull final Context context){
        if(isInitialise)
            return;
        FirebaseJobDispatcher dispatcher =new FirebaseJobDispatcher(new GooglePlayDriver(context));
        Job contraintReminderJob=dispatcher.newJobBuilder()
                .setTag(REMINDER_JOB_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setService(MyJobSchedule.class)
                .setTrigger(Trigger.executionWindow(REMINDER_TIME_SEC,REMINDER_TIME_SEC+SYN_FLEX_TIME))
                .setReplaceCurrent(true)
                .build();
        dispatcher.mustSchedule(contraintReminderJob);
        isInitialise=true;
    }


}

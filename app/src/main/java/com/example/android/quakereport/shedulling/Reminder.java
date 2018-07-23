package com.example.android.quakereport.shedulling;

import android.content.Context;

public class Reminder {

    public static final String job_schedule_string="shedule_job";

    public  static void execteTask(Context context,String action){
        if(job_schedule_string.equals(action)){
            issuejob(context);
        }
    }

    private static void issuejob(Context context) {
        NotificationUtils.remind(context);
    }
}

package com.example.android.quakereport.shedulling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.app.NotificationChannel;
import android.support.v4.app.NotificationCompat;

import com.example.android.quakereport.EarthquakeActivity;
import com.example.android.quakereport.R;

/**
 *
 */
public class NotificationUtils {

    private static final  int PendingId =3223;
    private static final int NotificationId=1123;
    private static final String notification_channel_id="Present";
    public  static String priamryLocation;
    public static String secondryLocation;

    public static void getPos(String a,String b){
        priamryLocation=a;
        secondryLocation=b;
    }
    public static void remind(Context context){
        if(priamryLocation!=null  && secondryLocation !=null ) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(notification_channel_id, "anurag", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(mChannel);
            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.magnitude_circle)
                    .setLargeIcon(largeIcon(context))
                    .setContentText(secondryLocation)
                    .setContentTitle(priamryLocation)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentIntent(contentIntent(context));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
            }
            notificationManager.notify(PendingId, notificationBuilder.build());
        }
    }

    public void clearNotification(Context context){
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    private static PendingIntent contentIntent(Context context){
        Intent intent=new Intent(context,EarthquakeActivity.class);

        return PendingIntent.getActivity(context,PendingId,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context){
        Resources res=context.getResources();
        Bitmap largeIcon= BitmapFactory.decodeResource(res,R.drawable.ic_filter);
        return largeIcon;
    }
}

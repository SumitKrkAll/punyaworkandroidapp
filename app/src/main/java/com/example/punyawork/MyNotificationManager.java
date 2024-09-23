package com.example.punyawork;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyNotificationManager extends AppCompatActivity {

    private static final int NOTIFICATION_ID=6000;
    private Context mcontext;
    private static MyNotificationManager myNotificationManager;
    private MyNotificationManager (Context context){
        mcontext=context;
    }
    public static synchronized MyNotificationManager getInstance(Context context){
        if(myNotificationManager==null){
            myNotificationManager=new MyNotificationManager(context);
        }
        return myNotificationManager;
    }
     public void DisplayNotification(String title,String body){


        // here is the code for the notification builder
         NotificationCompat.Builder builder= new NotificationCompat.Builder(mcontext,Constants.CHANNEL_ID);
    // the below line is neccessary for displaying icon above lollipop
         if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
             builder.setContentTitle(title)
                     .setContentText(body)
                     .setPriority(NotificationCompat.PRIORITY_HIGH)
                     .setVibrate(new long[] {0, 1000})
                     .setAutoCancel(true);
             builder.setSmallIcon(R.mipmap.ajpg5);
             builder.setColor(getResources().getColor(R.color.slategray));
         }else{
             builder.setContentTitle(title)
                     .setContentText(body)
                     .setPriority(NotificationCompat.PRIORITY_HIGH)
                     .setVibrate(new long[] {0, 1000})
                     .setAutoCancel(true)
                     .setSmallIcon(R.mipmap.ajpg5);
         }
         // add the action to startactivity when user click on it
         Intent intent = new Intent(mcontext,MainActivity2.class);
         PendingIntent pendingintent= PendingIntent.getActivity(mcontext,
                 0,
                 intent,
                 PendingIntent.FLAG_UPDATE_CURRENT);
         builder.setContentIntent(pendingintent);

         // issue the notification
         NotificationManager notificationManager=
                 (NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);

           if(notificationManager !=null){
               notificationManager.notify(NOTIFICATION_ID,builder.build());
           }
     }
     }



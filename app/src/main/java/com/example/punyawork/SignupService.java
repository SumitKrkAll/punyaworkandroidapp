package com.example.punyawork;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class SignupService extends IntentService {
    public static final String EXTRA_MESSAGE4="Body";
    public static final String EXTRA_MESSAGE5="Body2";
    public String text;
    public String text2;
    private static final int NOTIFICATION_ID=6006;
    public String title="You Signed Up Successfully.";
    public String title2="You Logged In Successfully.";
    public SignupService()
    {
        super("SignupService");
    }
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try {wait(1000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if(intent.hasExtra(EXTRA_MESSAGE4)){
            text=intent.getStringExtra(EXTRA_MESSAGE4);
            showText(text,title);
        }
        if(intent.hasExtra(EXTRA_MESSAGE5)){
            text=intent.getStringExtra(EXTRA_MESSAGE5);
            showText(text,title2);
        }


    }

    private void showText(String text,String title){
        // create the notification builder
        CharSequence name = getString(R.string.channel_name4);// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        String CHANNEL_ID = "my_channel_05";
        NotificationChannel mChannel = null;
        NotificationCompat.BigPictureStyle bigPictureStyle=new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.abedjpg5)).build();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);}
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setContentTitle(title)
                    .setContentText(text)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setVibrate(new long[] {0, 1000})
                    .setAutoCancel(true)
                    .setChannelId(CHANNEL_ID);
            builder.setStyle(bigPictureStyle);
            builder.setSmallIcon(R.mipmap.ajpg5);
            builder.setColor(getResources().getColor(R.color.slategray));
        } else {
            builder.setContentTitle(getString(R.string.question))
                    .setContentText(text)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setVibrate(new long[] {0, 1000})
                    .setAutoCancel(true)
                    .setChannelId(CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ajpg5);
        }

        // add the action to startactivity when user click on it
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingintent= PendingIntent.getActivity(this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingintent);
        // issue the notification
        NotificationManager notificationManager=
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(mChannel);
        }
        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }
}

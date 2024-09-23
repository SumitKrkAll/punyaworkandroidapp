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

public class Bed1MessageService extends IntentService {
    public static final String EXTRA_MESSAGE3="message";
    private static final int NOTIFICATION_ID=5455;

    public Bed1MessageService()
    {
        super("Bed1MessageService");
    }
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try {wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            } }

        String text= intent.getStringExtra(EXTRA_MESSAGE3);
        showText(text);
    }

    private void showText(String text){
        // create the notification builder
        CharSequence name = getString(R.string.channel_name3);// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        String CHANNEL_ID = "my_channel_04";
        NotificationChannel mChannel = null;
        NotificationCompat.BigPictureStyle bigPictureStyle=new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.abedjpg3)).build();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);}
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setContentTitle(getString(R.string.question))
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

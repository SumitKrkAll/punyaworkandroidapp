package com.example.punyawork;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class DelayedMessageService extends IntentService {
    public static final String EXTRA_MESSAGE="message";
    private static final int NOTIFICATION_ID=5453;

    public DelayedMessageService()
    {
        super("DelayedMessageService");
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try {wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            } }
        String text= intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);
    }
    private void showText(String text) {
        // create the notification builder
        CharSequence name = getString(R.string.channel_name); // The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        String CHANNEL_ID = "my_channel_01";
        NotificationChannel mChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle(getString(R.string.question))
                .setContentText(text + "\n Click for Trace Your Order.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{0, 1000})
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ajpg5)
                .setColor(getResources().getColor(R.color.slategray));

        // add the action to startActivity when user clicks on it
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Update the PendingIntent creation
        );
        builder.setContentIntent(pendingIntent);

        // issue the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(mChannel);
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}

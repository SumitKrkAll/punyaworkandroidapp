package com.example.punyawork;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title=remoteMessage.getNotification().getTitle();
        String body=remoteMessage.getNotification().getBody();
        MyNotificationManager.getInstance(getApplicationContext()).DisplayNotification(title,body);

    }
}

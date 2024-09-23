package com.example.punyawork;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

import static android.content.ContentValues.TAG;

public class MyFirebaseInstanceIdService extends FirebaseMessagingService {
    public void onNewToken(String token) {
        Log.d("My Firebase Token" , "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

    }
}

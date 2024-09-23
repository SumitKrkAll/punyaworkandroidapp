package com.example.punyawork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.firebase.FirebaseApp;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeScreen extends AppCompatActivity {
    private Handler mhandler;
    private Runnable myrunable;
    public FirebaseAuth myauth;
    public String Token;
    public String usermail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        FirebaseApp.initializeApp(this);
        Log.d("Message","Welcome to the log");
        try {
            myauth = FirebaseAuth.getInstance();
            myrunable=new Runnable() {
                @Override
                public void run() {
                    Log.e("Started Run method", "Started Run method");
                    if(myauth.getCurrentUser()== null){
                        Intent intent=new Intent(WelcomeScreen.this,SelectLoginAndSignupActivity.class);
                        startActivity(intent);
                    }else {
                        Intent myhome= new Intent(WelcomeScreen.this,MainActivity2.class);
                        startActivity(myhome);
                        finish();
                    }
                }
            };
            mhandler=new Handler();
            mhandler.postDelayed(myrunable,1000);
        } catch (Exception e) {
            Log.e("WelcomeScreen", "Error initializing FirebaseAuth", e);
        }


    }
    protected void onDestroy(){
        super.onDestroy();
        Log.e("Started destroy method", "Started destroy method");
        if(mhandler!=null && myrunable!=null){
            mhandler.removeCallbacks(myrunable);
        }
    }
}

package com.example.punyawork;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.app.NotificationChannel;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import java.util.Locale;
import android.content.pm.PackageManager;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.view.View;

public class DistanceDisplayActivity extends AppCompatActivity {
    private OdometerService odometerService;
    private boolean bound = false;
    private final int PERMISSION_REQUEST_CODE = 698;
    private static final int NOTIFICATION_ID=5453;
    private float distance = (float)0.0;
    private boolean wasbound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_display);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState!=null) {
            distance=savedInstanceState.getInt("second");
            bound=savedInstanceState.getBoolean("running");
            wasbound=savedInstanceState.getBoolean("wasrunning");
        }
        displayDistance();
    }
    public void onSaveInstanceState(Bundle a) {
        super.onSaveInstanceState(a);
        a.putFloat("distance", distance);
        a.putBoolean("bound", bound);
        a.putBoolean("wasbound", wasbound);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            OdometerService.OdometerBinder odometerBinder = (OdometerService.OdometerBinder) binder;
            odometerService = odometerBinder.getOdometer();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;


        }
    };

    protected void onStart() {
        super.onStart();
        if(ContextCompat.checkSelfPermission(this,odometerService.PERMISSION_STRING)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{odometerService.PERMISSION_STRING},PERMISSION_REQUEST_CODE);
        }else {

            Intent intent = new Intent(this, OdometerService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }
    public  void onRequestPermissionsResult(int requestCode,
                                            String permissions[],int[] grantResults){
        switch(requestCode){
            case PERMISSION_REQUEST_CODE: {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(this, OdometerService.class);
                    bindService(intent, connection, Context.BIND_AUTO_CREATE);

                } else {
                    CharSequence name = getString(R.string.channel_name1);// The user-visible name of the channel.
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    String CHANNEL_ID = "my_channel_01";
                    NotificationChannel mChannel = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                    }

                    NotificationCompat.Builder builder= new NotificationCompat.Builder(this)
                            .setSmallIcon(android.R.drawable.sym_def_app_icon)
                            .setContentTitle(getString(R.string.app_name))
                            .setContentText(getString(R.string.question1))
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setVibrate(new long[] {1000, 1000})
                            .setAutoCancel(true)
                            .setChannelId(CHANNEL_ID);
                    // add the action to startactivity when user click on it
                    Intent intent = new Intent(this,DistanceDisplayActivity.class);
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
        }

    }


    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;

        }
    }
    private void displayDistance(){
        final TextView distanceView=(TextView) findViewById(R.id.display);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                if(bound && odometerService !=null){
                    distance=odometerService.getDistance();
                }
                String distancestr= String.format(Locale.getDefault(),"%.2f Meter",distance);
                distanceView.setText(distancestr);
                handler.postDelayed(this,1000);


            }
        });

    }
    public void startm( View view){
        bound=true;
    }
    public void stopm(View view){

        bound = false;


    }
    public void restartm(View view){
        bound=false;
        distance = (float) 0.0;


    }
    protected void onPause() {
        super.onPause();
        wasbound=true;
        bound=false;
    }
    protected void onResume() {
        super.onResume();
        if (wasbound) {
            bound = true;
        }
    }
}
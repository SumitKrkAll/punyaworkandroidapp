package com.example.punyawork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopWatechActivity extends AppCompatActivity {
    private int second = 0;
    private boolean running ;
    private boolean wasrunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watech);
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState!=null) {
            second=savedInstanceState.getInt("second");
            running=savedInstanceState.getBoolean("running");
            wasrunning=savedInstanceState.getBoolean("wasrunning");
        }
        runTimer();
    }
    public void onSaveInstanceState(Bundle a) {
        super.onSaveInstanceState(a);
        a.putInt("second", second);
        a.putBoolean("running", running);
        a.putBoolean("wasrunning", wasrunning);
    }

    public void callstart(View view){
        running=true;
    }
    public void callstop(View view){
        running=false;
    }
    public void callrestart(View view){
        running=false;
        second=0;
    }
    private void runTimer () {
        final TextView text= (TextView) findViewById (R.id.view);
        final Handler handler= new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours= (second/3600);
                int minutes= ((second%3600)/60);
                int secs= (second%60);
                String time= String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                text.setText(time);
                if(running)
                { second++;}
                handler.postDelayed(this,1000);}
        });
    }
    // @override
    protected void onPause() {
        super.onPause();
        wasrunning=true;
        running=false;
    }
    protected void onResume() {
        super.onResume();
        if (wasrunning) {
            running = true;
        }
    }

}

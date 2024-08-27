package com.example.punyawork;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {
    public EditText name;
    public EditText email;
    public EditText subject;
    public EditText feedback;
    public String uname;
    public String uemail;
    public  String usubject;
    public String ufeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        name=(EditText) findViewById(R.id.editText6);
        email=(EditText)findViewById(R.id.editText7);
        subject=(EditText) findViewById(R.id.editText8);
        feedback=(EditText) findViewById(R.id.editText9);

    }
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.send, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case 8 :
            {
                uname=name.getText().toString();
                uemail=email.getText().toString();
                usubject=subject.getText().toString();
                ufeedback=feedback.getText().toString();
                //code to send feedback to us
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String subject= usubject;

                            String body= "<span>Name of the User is<span> "+uname+"<br>His Feedback is: "+ufeedback+"" +
                                    "<br><br>" +
                                    "<p>With Best Regards,</p>"+"" +
                                    "Aquaeasy User "+uname;

                            String fromemail=uemail;
                            String receiver="aquaeasy786@gmail.com";

                            GMailSender sender=new GMailSender ("punyawork1@gmail.com","ktjb xfia orgy wntp");
                            sender.sendMail(subject,body ,fromemail, receiver);
                        }catch (Exception e){
                            Log.e("SendMail",e.getMessage(),e); }
                    }

                }).start();
                Toast toast =Toast.makeText(this,"Message has been Sent to the Nearest Watersales Man",Toast.LENGTH_SHORT);
                toast.show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }



    }


    }



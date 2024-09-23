package com.example.punyawork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MySignupActivity extends AppCompatActivity {
    public EditText texte;
    public EditText pass;
    public String uemail;
    public String usermail;
    public String userpass;
    public ProgressBar progressBar;
    public FirebaseAuth myauth;
    public String Token;
    public String Body="Your Password is sent to Your Email-Id.Please Remember to use Our Services.";
    public String Body2="Please Remember your PASSWORD to use Our Services.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_signup);
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        myauth=FirebaseAuth.getInstance();
        texte=(EditText) findViewById(R.id.editText7);
        pass=(EditText) findViewById(R.id.editText9);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        uemail=texte.getText().toString();
        userpass=pass.getText().toString();
    }

    public void mysignup(View view){
        uemail=texte.getText().toString();
        userpass=pass.getText().toString();
        if (texte.length()==0){
            texte.setError("Enter Email Address");
            texte.requestFocus();
        }else if (pass.length()==0){
            pass.setError("Enter Password");
            pass.requestFocus();
        }else if(pass.length()<6){
            pass.setError("Password Should be atleast 6 char long");
            pass.requestFocus();
        } else{
            uemail=texte.getText().toString();
            userpass=pass.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            myauth.createUserWithEmailAndPassword(uemail,userpass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent1=new Intent(MySignupActivity.this,SignupService.class);
                                intent1.putExtra(SignupService.EXTRA_MESSAGE4,Body);
                                startService(intent1);
                                sendmailfunc(uemail,userpass);
                                startyouractivity();
                            }else{
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MySignupActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    public void startyouractivity(){
        Intent intent=new Intent(this,MainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void sendmailfunc(final String uemail, final String userpass){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String subject= "S & C Private Limited";
                    String body="Thanks for Signed Up in our App.Your Password is  "+userpass+".";
                    String eamilsender="punyawork1@gmail.com";
                    String receiver=uemail;
                    GMailSender sender=new GMailSender ("punyawork1@gmail.com","ktjb xfia orgy wntp");
                    sender.sendMail(subject,body ,eamilsender, receiver);
                }catch (Exception e){
                    Log.e("SendMail",e.getMessage(),e); } }
        }).start();
    }
}

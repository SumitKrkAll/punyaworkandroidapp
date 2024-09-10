package com.example.punyawork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectLoginAndSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login_and_signup);
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
    }

    public void mysignupfunc(View view){
        Intent intent=new Intent(SelectLoginAndSignupActivity.this,MySignupActivity.class);
        startActivity(intent);
    }

    public void myloginfunc (View view){
        Intent intent=new Intent(SelectLoginAndSignupActivity.this,MyLoginActivity.class);
        startActivity(intent);

    }
}

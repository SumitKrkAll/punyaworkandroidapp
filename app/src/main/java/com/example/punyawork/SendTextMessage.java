package com.example.punyawork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendTextMessage extends AppCompatActivity {
    private EditText editTextnumber;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text_message);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (ContextCompat.checkSelfPermission(SendTextMessage.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SendTextMessage.this, new String[] { Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS}, 0);}
        editTextnumber=(EditText) findViewById(R.id.editText);
        editTextMessage=(EditText) findViewById(R.id.editText2);
    }
    public void sendSms(View view) {
        if (editTextnumber.length() == 0) {
            editTextnumber.setError("Enter Mobile Number");
            editTextnumber.requestFocus();
        } else if (editTextnumber.length() < 10) {
            editTextnumber.setError("Enter Ten Digit Mobile Number");
            editTextnumber.requestFocus();
        } else if (editTextMessage.length() == 0) {
            editTextMessage.setError("Enter Text Message");
        } else {
            String usermessage = editTextMessage.getText().toString();
            String userMobile = editTextnumber.getText().toString();
            SmsManager mysmsManager = SmsManager.getDefault();
            mysmsManager.sendTextMessage(userMobile, null, usermessage, null, null);
            editTextnumber.setText("");
            editTextMessage.setText("");
            editTextnumber.requestFocus();
            Toast.makeText(this, "Your message has been send to the user", Toast.LENGTH_SHORT).show();

        }
    }
}

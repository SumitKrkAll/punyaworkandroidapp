package com.example.punyawork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditDetailActivity extends AppCompatActivity {
    public EditText textn;
    public EditText texte;
    public EditText textp;
    public EditText texta;
    public String uname;
    public String uemail;
    public String uphone;
    public String uaddress;
    public ContentValues contentValues=new ContentValues();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
         textn=(EditText) findViewById(R.id.editText6);
         texte=(EditText) findViewById(R.id.editText7);
         textp=(EditText) findViewById(R.id.editText8);
         texta=(EditText) findViewById(R.id.editText9);
        uname=textn.getText().toString();
        uemail=texte.getText().toString();
        uphone=textp.getText().toString();
        uaddress=texta.getText().toString();
    }


    public void myupdatedetail(View view){
        if(textn.length()==0){
            textn.setError("Enter Full Name");
        }else if (texte.length()==0){
            texte.setError("Enter Email Address");
        }else if (textp.length()==0){
            textp.setError("Enter Phone Number");
        } else {
            textn=(EditText) findViewById(R.id.editText6);
            texte=(EditText) findViewById(R.id.editText7);
            textp=(EditText) findViewById(R.id.editText8);
            texta=(EditText) findViewById(R.id.editText9);
            uname=textn.getText().toString();
            uemail=texte.getText().toString();
            uphone=textp.getText().toString();
            uaddress=texta.getText().toString();
            contentValues.put("USER_NAME",uname);
            contentValues.put("USER_EMAIL",uemail);
            contentValues.put("USER_Mobile_No",uphone);
            contentValues.put("USER_ADDRESS",uaddress);

            SQLiteOpenHelper aquaeasyDatabasehelper=new AquaeasyDatabaseHelper(this);
            try{
                SQLiteDatabase db = aquaeasyDatabasehelper.getWritableDatabase();
                long result= db.insert("UserDetail",null,contentValues);
                if(result==-1){
                    Toast toast2= Toast.makeText(EditDetailActivity.this,
                            "Data  is not inserted into the UserDetail", Toast.LENGTH_SHORT);
                    toast2.show();
                }else {
                    Toast toast3= Toast.makeText(EditDetailActivity.this,
                            "Your Details are Updated Successfully", Toast.LENGTH_SHORT);
                    toast3.show();
                }
                db.close();
                Intent mymainintent=new Intent(EditDetailActivity.this,MainActivity2.class);
                startActivity(mymainintent);
            } catch (SQLiteException e){
                Toast toast1= Toast.makeText(EditDetailActivity.this,
                        "Database is Unavailable", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }

    }
}

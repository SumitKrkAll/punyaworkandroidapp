package com.example.punyawork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

public class FinalPlatformDetailActivity extends AppCompatActivity {
    public static final String PLATFORM_FINAL="PLATFORMWATER";
    public TextView text5;
    public TextView text32;
    public int id;
    public String pname;
    public int platformno;
    public String ADNno;
    public int quantity;
    public int noofbottle;
    public int noofpacket;
    public int noofbed;
    public int totalp;
    public String modepay;
    public String category;
    public String brandname;
    public String mobno;
    public String orderDatepf;
    public String orderTimepf;
    public int num=0;
    public String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_platform_detail);
        androidx.appcompat.widget.Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // create a database reference
        SQLiteOpenHelper aquaeasyDatabaseHelper = new AquaeasyDatabaseHelper(this);
        if (checknetworkconnection()) {
            try {
                SQLiteDatabase db = aquaeasyDatabaseHelper.getWritableDatabase();
                Cursor cursor = db.query("PlatformWaterOrder",
                        new String[]{"Id", "Passenger_Name", "PLATFORM_No", "ADN_No", "Quantity", "Total_Price",
                                "Payment_Mode", "Category", "Product_Name", "Mobile_No", "Number_of_Bottle",
                                "Number_of_Packet", "Number_of_Bed","Order_Date","Order_Time"},
                        null, null,
                        null, null, null);
                if (cursor.moveToLast()) {
                    id = cursor.getInt(0);
                    TextView text1 = (TextView) findViewById(R.id.textView25);
                    text1.setText(Integer.toString((id+10000)));
                    pname = cursor.getString(1);
                    TextView text2 = (TextView) findViewById(R.id.textView27);
                    text2.setText(pname);
                    platformno = cursor.getInt(2);
                    TextView text3 = (TextView) findViewById(R.id.textView29);
                    text3.setText(Integer.toString(platformno));
                    ADNno = cursor.getString(3);
                    TextView text4 = (TextView) findViewById(R.id.textView31);
                    text4.setText(ADNno);
                    if (!(cursor.isNull(4))) {
                        quantity = cursor.getInt(4);
                        text5 = (TextView) findViewById(R.id.textView33);
                        text5.setText(Integer.toString(quantity));
                        num = 0;
                    } else if (!(cursor.isNull(10))) {
                        text32 = (TextView) findViewById(R.id.textView32);
                        text32.setText("No. of Bottle:");
                        noofbottle = cursor.getInt(10);
                        text5 = (TextView) findViewById(R.id.textView33);
                        text5.setText(Integer.toString(noofbottle));
                        num = 1;
                    } else if (!(cursor.isNull(11))) {
                        text32 = (TextView) findViewById(R.id.textView32);
                        text32.setText("No. of Packet:");
                        noofpacket = cursor.getInt(11);
                        text5 = (TextView) findViewById(R.id.textView33);
                        text5.setText(Integer.toString(noofpacket));
                        num = 2;
                    } else if (!(cursor.isNull(12))) {
                        text32 = (TextView) findViewById(R.id.textView32);
                        text32.setText("No. of Bed:");
                        noofbed = cursor.getInt(12);
                        text5 = (TextView) findViewById(R.id.textView33);
                        text5.setText(Integer.toString(noofbed));
                        num = 3;
                    }
                    totalp = cursor.getInt(5);
                    TextView text6 = (TextView) findViewById(R.id.textView35);
                    text6.setText(Integer.toString(totalp));
                    modepay = cursor.getString(6);
                    TextView text7 = (TextView) findViewById(R.id.textView37);
                    text7.setText(modepay);
                    category = cursor.getString(7);
                    TextView text8 = (TextView) findViewById(R.id.textView41);
                    text8.setText(category);
                    brandname = cursor.getString(8);
                    TextView text9 = (TextView) findViewById(R.id.textView43);
                    text9.setText(brandname);
                    mobno = cursor.getString(9);
                    TextView text10 = (TextView) findViewById(R.id.textView45);
                    text10.setText(mobno);
                    orderDatepf = cursor.getString(13);
                    TextView text11 = (TextView) findViewById(R.id.textView61);
                    text11.setText(orderDatepf);
                    orderTimepf = cursor.getString(14);
                    TextView text12 = (TextView) findViewById(R.id.textView59);
                    text12.setText(orderTimepf);
                }
                TextView text8 = (TextView) findViewById(R.id.textView39);
                text8.setText("Success");


                cursor.close();
                db.close();

            } catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "Database unavilable", Toast.LENGTH_SHORT);
                toast.show();
            }

            // code to send mail
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String subject = "S & C Private Limited";
                        if (num == 0) {
                            body = "<!DOCTYPE html>\n" +
                                    "<html>\n" +
                                    "<head>\n" +
                                    "<style>\n" +
                                    "table {\n" +
                                    "  border-collapse: collapse;\n" +
                                    "  width: 100%;\n" +
                                    "}\n" +
                                    "\n" +
                                    "th, td {\n" +
                                    "  padding: 8px;\n" +
                                    "  text-align: left;\n" +
                                    "  border: 1px solid black ;\n" +
                                    "}\n" +
                                    "</style>\n" +
                                    "</head>\n" +
                                    "<body>\n" +
                                    "<div class=\"w3-container\">\n" +
                                    "  <h4>Please Deliver Water Order To Following Details.</h4>\n" +
                                    "      <table class=\"w3-table w3-striped w3-bordered\">\n" +
                                    "                              <tr>\n" +
                                    "                                <th>Field</th>\n" +
                                    "                                <th>Value</th>\n" +
                                    "                              </tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Date:</td>\n" +
                                    "<td>" + orderDatepf + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Time:</td>\n" +
                                    "<td>" + orderTimepf + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Id:</td>\n" +
                                    "<td>" + id + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Category:</td>\n" +
                                    "<td>" + category + "</td>\n" +
                                    "</tr>\n" +

                                    "<tr>\n" +
                                    "<td>Brand:</td>\n" +
                                    "<td>" + brandname + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Name:</td>\n" +
                                    "<td>" + pname + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Platform No.:</td>\n" +
                                    "<td>" + platformno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>ADN No.:</td>\n" +
                                    "<td>" + ADNno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Mobile NO.:</td>\n" +
                                    "<td>" + mobno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Quantity:</td>\n" +
                                    "<td>" + quantity + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Total Price:</td>\n" +
                                    "<td>" + totalp + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Mode of Payment:</td>\n" +
                                    "<td>" + modepay + "</td>\n" +
                                    "</tr>\n" +

                                    "                            </table>\n" +
                                    "                              <br> <br>\n" +
                                    "                           <p>With Best Regards,</p>\n" +
                                    "                              <p>Aquaeasy Team</p>\n" +
                                    "</body>\n" +
                                    "</html>";
                        }
                        if (num == 1) {
                            body = "<!DOCTYPE html>\n" +
                                    "<html>\n" +
                                    "<head>\n" +
                                    "<style>\n" +
                                    "table {\n" +
                                    "  border-collapse: collapse;\n" +
                                    "  width: 100%;\n" +
                                    "}\n" +
                                    "\n" +
                                    "th, td {\n" +
                                    "  padding: 8px;\n" +
                                    "  text-align: left;\n" +
                                    "  border: 1px solid black ;\n" +
                                    "}\n" +
                                    "</style>\n" +
                                    "</head>\n" +
                                    "<body>\n" +
                                    "<div class=\"w3-container\">\n" +
                                    "  <h4>Please Deliver Water Order To Following Details.</h4>\n" +
                                    "      <table class=\"w3-table w3-striped w3-bordered\">\n" +
                                    "                              <tr>\n" +
                                    "                                <th>Field</th>\n" +
                                    "                                <th>Value</th>\n" +
                                    "                              </tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Date:</td>\n" +
                                    "<td>" + orderDatepf + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Time:</td>\n" +
                                    "<td>" + orderTimepf + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Id:</td>\n" +
                                    "<td>" + id + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Category:</td>\n" +
                                    "<td>" + category + "</td>\n" +
                                    "</tr>\n" +

                                    "<tr>\n" +
                                    "<td>Brand:</td>\n" +
                                    "<td>" + brandname + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Name:</td>\n" +
                                    "<td>" + pname + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Platform No.:</td>\n" +
                                    "<td>" + platformno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>ADN No.:</td>\n" +
                                    "<td>" + ADNno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Mobile NO.:</td>\n" +
                                    "<td>" + mobno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>No. of Bottle:</td>\n" +
                                    "<td>" + noofbottle + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Total Price:</td>\n" +
                                    "<td>" + totalp + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Mode of Payment:</td>\n" +
                                    "<td>" + modepay + "</td>\n" +
                                    "</tr>\n" +

                                    "                            </table>\n" +
                                    "                               <br> <br>\n" +
                                    "                           <p>With Best Regards,</p>\n" +
                                    "                              <p>Aquaeasy Team</p>\n" +
                                    "</body>\n" +
                                    "</html>";
                        }
                        if (num == 2) {
                            body = "<!DOCTYPE html>\n" +
                                    "<html>\n" +
                                    "<head>\n" +
                                    "<style>\n" +
                                    "table {\n" +
                                    "  border-collapse: collapse;\n" +
                                    "  width: 100%;\n" +
                                    "}\n" +
                                    "\n" +
                                    "th, td {\n" +
                                    "  padding: 8px;\n" +
                                    "  text-align: left;\n" +
                                    "  border: 1px solid black ;\n" +
                                    "}\n" +
                                    "</style>\n" +
                                    "</head>\n" +
                                    "<body>\n" +
                                    "<div class=\"w3-container\">\n" +
                                    "  <h4>Please Deliver Water Order To Following Details.</h4>\n" +
                                    "      <table class=\"w3-table w3-striped w3-bordered\">\n" +
                                    "                              <tr>\n" +
                                    "                                <th>Field</th>\n" +
                                    "                                <th>Value</th>\n" +
                                    "                              </tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Date:</td>\n" +
                                    "<td>" + orderDatepf + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Time:</td>\n" +
                                    "<td>" + orderTimepf + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Id:</td>\n" +
                                    "<td>" + id + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Category:</td>\n" +
                                    "<td>" + category + "</td>\n" +
                                    "</tr>\n" +

                                    "<tr>\n" +
                                    "<td>Brand:</td>\n" +
                                    "<td>" + brandname + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Name:</td>\n" +
                                    "<td>" + pname + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Platform No.:</td>\n" +
                                    "<td>" + platformno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>ADN No.:</td>\n" +
                                    "<td>" + ADNno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Mobile NO.:</td>\n" +
                                    "<td>" + mobno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>No. of Packet:</td>\n" +
                                    "<td>" + noofpacket + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Total Price:</td>\n" +
                                    "<td>" + totalp + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Mode of Payment:</td>\n" +
                                    "<td>" + modepay + "</td>\n" +
                                    "</tr>\n" +

                                    "                            </table>\n" +
                                    "                             <br> <br>\n" +
                                    "                           <p>With Best Regards,</p>\n" +
                                    "                              <p>Aquaeasy Team</p>\n" +
                                    "</body>\n" +
                                    "</html>";
                        }
                        if (num == 3) {
                            body = "<!DOCTYPE html>\n" +
                                    "<html>\n" +
                                    "<head>\n" +
                                    "<style>\n" +
                                    "table {\n" +
                                    "  border-collapse: collapse;\n" +
                                    "  width: 100%;\n" +
                                    "}\n" +
                                    "\n" +
                                    "th, td {\n" +
                                    "  padding: 8px;\n" +
                                    "  text-align: left;\n" +
                                    "  border: 1px solid black ;\n" +
                                    "}\n" +
                                    "</style>\n" +
                                    "</head>\n" +
                                    "<body>\n" +
                                    "<div class=\"w3-container\">\n" +
                                    "  <h4>Please Deliver Water Order To Following Details.</h4>\n" +
                                    "      <table class=\"w3-table w3-striped w3-bordered\">\n" +
                                    "                              <tr>\n" +
                                    "                                <th>Field</th>\n" +
                                    "                                <th>Value</th>\n" +
                                    "                              </tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Date:</td>\n" +
                                    "<td>" + orderDatepf + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Time:</td>\n" +
                                    "<td>" + orderTimepf + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Order Id:</td>\n" +
                                    "<td>" + id + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Category:</td>\n" +
                                    "<td>" + category + "</td>\n" +
                                    "</tr>\n" +

                                    "<tr>\n" +
                                    "<td>Brand:</td>\n" +
                                    "<td>" + brandname + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Name:</td>\n" +
                                    "<td>" + pname + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Platform No.:</td>\n" +
                                    "<td>" + platformno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>ADN No.:</td>\n" +
                                    "<td>" + ADNno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Mobile NO.:</td>\n" +
                                    "<td>" + mobno + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>No. of Bed:</td>\n" +
                                    "<td>" + noofbed + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Total Price:</td>\n" +
                                    "<td>" + totalp + "</td>\n" +
                                    "</tr>\n" +
                                    "<tr>\n" +
                                    "<td>Mode of Payment:</td>\n" +
                                    "<td>" + modepay + "</td>\n" +
                                    "</tr>\n" +

                                    "                            </table>\n" +
                                    "                             <br> <br>\n" +
                                    "                           <p>With Best Regards,</p>\n" +
                                    "                              <p>Aquaeasy Team</p>\n" +
                                    "</body>\n" +
                                    "</html>";
                        }

                        String eamilsender = "sumitkumar5may1998@gmail.com";
                        String receiver = "aquaeasy786@gmail.com";

                        GMailSender sender = new GMailSender("sumitkumar5may1998@gmail.com", "MYAQUAjindabad@786");
                        sender.sendMail(subject, body, eamilsender, receiver);
                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }
                }

            }).start();
            Toast toast = Toast.makeText(this, "Message has been Sent to the Nearest Watersales Man", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void gotohome(View view){
        Intent intent =new Intent(this,Order_History.class);
        startActivity(intent);
    }

    public boolean checknetworkconnection(){
        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
    }


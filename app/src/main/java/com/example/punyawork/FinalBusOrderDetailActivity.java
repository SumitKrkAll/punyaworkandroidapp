package com.example.punyawork;

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
import android.net.Uri;

import java.io.File;

public class FinalBusOrderDetailActivity extends AppCompatActivity {
    public static final String BUS_FINAL="BUSWATER";
    public TextView text5;
    public TextView text32;
    public int id;
    public int busno;
    public int seatno;
    public int busstop;
    public int quantity;
    public int noofbottle;
    public int noofpacket;
    public int totalp;
    public String modepay;
    public String category;
    public String brandname;
    public int num=0;
    public String body;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_bus_order_detail);
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // create a database reference
        SQLiteOpenHelper aquaeasyDatabaseHelper=new AquaeasyDatabaseHelper(this);
        try {
            SQLiteDatabase db = aquaeasyDatabaseHelper.getWritableDatabase();
            Cursor cursor=db.query("BusWaterOrder",
                    new String[]{"Id","BUS_NO ","Seat_No","Quantity","Total_Price",
                            "Payment_Mode","Category","Product_Name","Number_of_Bottle",
                            "Number_of_Packet","BUS_STOP_NO "},
                    null,null,
                    null,null,null);
            if(cursor.moveToLast()){
                 id= cursor.getInt(0);
                TextView text1=(TextView) findViewById(R.id.textView25);
                text1.setText(Integer.toString(id));
                 busno=cursor.getInt(1);
                TextView text2=(TextView) findViewById(R.id.textView27);
                text2.setText(Integer.toString(busno));
                seatno=cursor.getInt(2);
                TextView text4=(TextView) findViewById(R.id.textView31);
                text4.setText(Integer.toString(seatno));
                 busstop= cursor.getInt(10);
                TextView text45=(TextView) findViewById(R.id.textView45);
                text45.setText(Integer.toString(busstop));
                if(!(cursor.isNull(3))){
                    text32=(TextView) findViewById(R.id.textView32);
                    text32.setText("Quantity(L):");
                     quantity=cursor.getInt(3);
                    text5=(TextView) findViewById(R.id.textView33);
                    text5.setText(Integer.toString(quantity));
                    num=1;
                }else if (!(cursor.isNull(8))){
                    text32=(TextView) findViewById(R.id.textView32);
                    text32.setText("No. of Bottle:");
                     noofbottle=cursor.getInt(8);
                    text5=(TextView) findViewById(R.id.textView33);
                    text5.setText(Integer.toString(noofbottle));
                    num=2;
                }else if(!(cursor.isNull(9))) {
                    text32=(TextView) findViewById(R.id.textView32);
                    text32.setText("No. of Packet:");
                     noofpacket=cursor.getInt(9);
                    text5=(TextView) findViewById(R.id.textView33);
                    text5.setText(Integer.toString(noofpacket));
                    num=3;
                }

                 totalp=cursor.getInt(4);
                TextView text6=(TextView) findViewById(R.id.textView35);
                text6.setText(Integer.toString(totalp));
                 modepay=cursor.getString(5);
                TextView text7=(TextView) findViewById(R.id.textView37);
                text7.setText(modepay);
                 category = cursor.getString(6);
                TextView text8=(TextView) findViewById(R.id.textView41);
                text8.setText(category);
                 brandname =cursor.getString(7);
                TextView text9 = (TextView) findViewById(R.id.textView43);
                text9.setText(brandname);}
            TextView text8=(TextView) findViewById(R.id.textView39);
            text8.setText("Success");
            cursor.close();
            db.close();
        }catch(SQLiteException e){
            Toast toast=Toast.makeText(this,"Database unavilable",Toast.LENGTH_SHORT);
            toast.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String subject= "S & C Private Limited";
                    if(num==1){
                        body="<!DOCTYPE html>\n" +
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
                                "<td>Order Id:</td>\n" +
                                "<td>"+id+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Category:</td>\n" +
                                "<td>"+category+"</td>\n" +
                                "</tr>\n"+

                                "<tr>\n" +
                                "<td>Brand:</td>\n" +
                                "<td>"+brandname+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Bus No.:</td>\n" +
                                "<td>"+busno+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Seat No.:</td>\n" +
                                "<td>"+seatno+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Bus Stand NO.:</td>\n" +
                                "<td>"+busstop+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Quantity(L):</td>\n" +
                                "<td>"+quantity+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Total Price:</td>\n" +
                                "<td>"+totalp+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Mode of Payment:</td>\n" +
                                "<td>"+modepay+"</td>\n" +
                                "</tr>\n"+

                                "                            </table>\n" +
                                "                              <br> <br>\n" +
                                "                           <p>With Best Regards,</p>\n" +
                                "                              <p>Aquaeasy Team</p>\n" +
                                "</body>\n" +
                                "</html>";
                    }
                    if(num==2){
                        body="<!DOCTYPE html>\n" +
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
                                "<td>Order Id:</td>\n" +
                                "<td>"+id+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Category:</td>\n" +
                                "<td>"+category+"</td>\n" +
                                "</tr>\n"+

                                "<tr>\n" +
                                "<td>Brand:</td>\n" +
                                "<td>"+brandname+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Bus No.:</td>\n" +
                                "<td>"+busno+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Seat No.:</td>\n" +
                                "<td>"+seatno+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Bus Stand NO.:</td>\n" +
                                "<td>"+busstop+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>No. of Bottle:</td>\n" +
                                "<td>"+noofbottle+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Total Price:</td>\n" +
                                "<td>"+totalp+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Mode of Payment:</td>\n" +
                                "<td>"+modepay+"</td>\n" +
                                "</tr>\n"+

                                "                            </table>\n" +
                                "                              <br> <br>\n" +
                                "                           <p>With Best Regards,</p>\n" +
                                "                              <p>Aquaeasy Team</p>\n" +
                                "</body>\n" +
                                "</html>";
                    }
                    if(num==3){
                        body="<!DOCTYPE html>\n" +
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
                                "<td>Order Id:</td>\n" +
                                "<td>"+id+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Category:</td>\n" +
                                "<td>"+category+"</td>\n" +
                                "</tr>\n"+

                                "<tr>\n" +
                                "<td>Brand:</td>\n" +
                                "<td>"+brandname+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Bus No.:</td>\n" +
                                "<td>"+busno+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Seat No.:</td>\n" +
                                "<td>"+seatno+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Bus Stand NO.:</td>\n" +
                                "<td>"+busstop+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>No. of Packet:</td>\n" +
                                "<td>"+noofpacket+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Total Price:</td>\n" +
                                "<td>"+totalp+"</td>\n" +
                                "</tr>\n"+
                                "<tr>\n" +
                                "<td>Mode of Payment:</td>\n" +
                                "<td>"+modepay+"</td>\n" +
                                "</tr>\n"+

                                "                            </table>\n" +
                                "                             <br> <br>\n" +
                                "                           <p>With Best Regards,</p>\n" +
                                "                              <p>Aquaeasy Team</p>\n" +
                                "</body>\n" +
                                "</html>";
                    }

                    String eamilsender="punyawork1@gmail.com";
                    String receiver="aquaeasy786@gmail.com";

                    GMailSender sender=new GMailSender ("punyawork1@gmail.com","ktjb xfia orgy wntp");
                    sender.sendMail(subject,body ,eamilsender, receiver);
                }catch (Exception e){
                    Log.e("SendMail",e.getMessage(),e); }
            }

        }).start();
        Toast toast =Toast.makeText(this,"Message has been Sent to the Nearest Water Salesman.",Toast.LENGTH_SHORT);
        toast.show();












    }
    public void gotohome(View view){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}

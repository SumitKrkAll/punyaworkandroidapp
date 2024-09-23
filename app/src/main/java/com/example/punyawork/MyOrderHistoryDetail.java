package com.example.punyawork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOrderHistoryDetail extends AppCompatActivity {
    public static final String ORDER_ID = "position";
    ArrayList<Contacts> arrayList=new ArrayList<>();
    Recycleradapter adapter;
    public TextView text5;
    public TextView text32;
    public int id;
    public int trainno;
    public String coachno;
    public int seatno;
    public int quantity;
    public int noofbottle;
    public int noofpacket;
    public int totalp;
    public String modepay;
    public String category;
    public String brandname;
    public String pnrno;
    public String orderdate;
    public String ordertime;
    public int num=0;
    public String body;


    public TextView text5b;
    public TextView text32b;
    public int idb;
    public int busnob;
    public int seatnob;
    public int busstopb;
    public int quantityb;
    public int noofbottleb;
    public int noofpacketb;
    public int totalpb;
    public String modepayb;
    public String categoryb;
    public String brandnameb;
    public String orderDateb;
    public String orderTimeb;
    public int numb=0;
    public String bodyb;


    public TextView text5pf;
    public TextView text32pf;
    public int idpf;
    public String pnamepf;
    public int platformnopf;
    public String ADNnopf;
    public int quantitypf;
    public int noofbottlepf;
    public int noofpacketpf;
    public int noofbedpf;
    public int totalppf;
    public String modepaypf;
    public String categorypf;
    public String brandnamepf;
    public String mobnopf;
    public String orderDatepf;
    public String orderTimepf;
    public int numpf=0;
    public String bodypf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_history_detail);
        androidx.appcompat.widget.Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        int position= (Integer)getIntent().getExtras().get(ORDER_ID);
        adapter=new Recycleradapter(arrayList);
        readinfofromalllocaldatabase();
        int orderid=adapter.ReturnOrderId(position);
        if((orderid>100)&&(orderid<1000)) {
            readdatfromTrainTable(orderid);
        }else if((orderid>1000)&&(orderid<10000)){
            readfrombusTable(orderid);
        }else{
            readfromplatformtable(orderid);
        }


        }

    public void readinfofromalllocaldatabase(){
        arrayList.clear();
        SQLiteOpenHelper aquaeasyDatabaseHelper=new AquaeasyDatabaseHelper(this);
        SQLiteDatabase db = aquaeasyDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query("TrainWaterOrder",
                new String[]{"Id","Category","Sync_Status","Order_Date"},
                null, null,
                null, null, null);

        while (cursor.moveToNext()){
            String Categoryname=cursor.getString(1);
            int Syncstatus=cursor.getInt(2);
            int OrderId=100+cursor.getInt(0);
            String Order_Date=cursor.getString(3);
            arrayList.add(new Contacts(OrderId,Syncstatus,Categoryname,Order_Date));
        }

        Cursor cursor1 = db.query("BusWaterOrder",
                new String[]{"Id","Category","Sync_Status","Order_Date"},
                null, null,
                null, null, null);
        while (cursor1.moveToNext()){
            String Categoryname=cursor1.getString(1);
            int Syncstatus=cursor1.getInt(2);
            int OrderId=1000+cursor1.getInt(0);
            String Order_Date=cursor1.getString(3);
            arrayList.add(new Contacts(OrderId,Syncstatus,Categoryname,Order_Date));
        }
        Cursor cursor2 = db.query("PlatformWaterOrder",
                new String[]{"Id","Category","Sync_Status","Order_Date"},
                null, null,
                null, null, null);
        while (cursor2.moveToNext()){
            String Categoryname=cursor2.getString(1);
            int Syncstatus=cursor2.getInt(2);
            int OrderId=10000+cursor2.getInt(0);
            String Order_Date=cursor2.getString(3);
            arrayList.add(new Contacts(OrderId,Syncstatus,Categoryname,Order_Date));
        }
        adapter.notifyDataSetChanged();
        cursor.close();
        aquaeasyDatabaseHelper.close();
    }

    public void readdatfromTrainTable(int orderid){
        setContentView(R.layout.activity_final_order_detail);
        androidx.appcompat.widget.Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        SQLiteOpenHelper aquaeasyDatabaseHelper = new AquaeasyDatabaseHelper(this);
        try {
            SQLiteDatabase db = aquaeasyDatabaseHelper.getWritableDatabase();
            Cursor cursor = db.query("TrainWaterOrder",
                    new String[]{"Id", "Train_No", "Coach_No", "Seat_No", "Quantity", "Total_Price",
                            "Payment_Mode", "Category", "Product_Name", "PNR_No", "Number_of_Bottle",
                            "Number_of_Packet","Order_Date","Order_Time"},
                    "Id=?", new String []{Integer.toString((orderid-100))},
                    null, null, null);
            if (cursor.moveToLast()) {
                id = cursor.getInt(0);
                TextView text1 = (TextView) findViewById(R.id.textView25);
                text1.setText(Integer.toString((id+100)));
                trainno = cursor.getInt(1);
                TextView text2 = (TextView) findViewById(R.id.textView27);
                text2.setText(Integer.toString(trainno));
                coachno = cursor.getString(2);
                TextView text3 = (TextView) findViewById(R.id.textView29);
                text3.setText(coachno);
                seatno = cursor.getInt(3);
                TextView text4 = (TextView) findViewById(R.id.textView31);
                text4.setText(Integer.toString(seatno));
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
                pnrno = cursor.getString(9);
                TextView text10 = (TextView) findViewById(R.id.textView45);
                text10.setText(pnrno);
                TextView text11 = (TextView) findViewById(R.id.textView54);
                orderdate=cursor.getString(12);
                text11.setText(orderdate);
                TextView text12 = (TextView) findViewById(R.id.textView52);
                ordertime=cursor.getString(13);
                text12.setText(ordertime);
            }
            TextView text8 = (TextView) findViewById(R.id.textView39);
            text8.setText("Success");


            cursor.close();
            db.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavilable", Toast.LENGTH_SHORT);
            toast.show();
        }


    }


   public void readfrombusTable(int orderid){
       setContentView(R.layout.activity_final_bus_order_detail);
       androidx.appcompat.widget.Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
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
                           "Number_of_Packet","BUS_STOP_NO","Order_Date","Order_Time"},
                   "Id=?", new String []{Integer.toString((orderid-1000))},
                   null,null,null);
           if(cursor.moveToLast()){
               idb= cursor.getInt(0);
               TextView text1=(TextView) findViewById(R.id.textView25);
               text1.setText(Integer.toString(idb));
               busnob=cursor.getInt(1);
               TextView text2=(TextView) findViewById(R.id.textView27);
               text2.setText(Integer.toString(busnob));
               seatnob=cursor.getInt(2);
               TextView text4=(TextView) findViewById(R.id.textView31);
               text4.setText(Integer.toString(seatnob));
               busstopb= cursor.getInt(10);
               TextView text45=(TextView) findViewById(R.id.textView45);
               text45.setText(Integer.toString(busstopb));
               if(!(cursor.isNull(3))){
                   text32b=(TextView) findViewById(R.id.textView32);
                   text32b.setText("Quantity(L):");
                   quantityb=cursor.getInt(3);
                   text5b=(TextView) findViewById(R.id.textView33);
                   text5b.setText(Integer.toString(quantityb));
                   numb=1;
               }else if (!(cursor.isNull(8))){
                   text32b=(TextView) findViewById(R.id.textView32);
                   text32b.setText("No. of Bottle:");
                   noofbottleb=cursor.getInt(8);
                   text5b=(TextView) findViewById(R.id.textView33);
                   text5b.setText(Integer.toString(noofbottleb));
                   numb=2;
               }else if(!(cursor.isNull(9))) {
                   text32b=(TextView) findViewById(R.id.textView32);
                   text32b.setText("No. of Packet:");
                   noofpacketb=cursor.getInt(9);
                   text5b=(TextView) findViewById(R.id.textView33);
                   text5b.setText(Integer.toString(noofpacketb));
                   numb=3;
               }

               totalpb=cursor.getInt(4);
               TextView text6=(TextView) findViewById(R.id.textView35);
               text6.setText(Integer.toString(totalpb));
               modepayb=cursor.getString(5);
               TextView text7=(TextView) findViewById(R.id.textView37);
               text7.setText(modepayb);
               categoryb = cursor.getString(6);
               TextView text8=(TextView) findViewById(R.id.textView41);
               text8.setText(categoryb);
               brandnameb =cursor.getString(7);
               TextView text9 = (TextView) findViewById(R.id.textView43);
               text9.setText(brandnameb);
               orderDateb =cursor.getString(11);
               TextView text10 = (TextView) findViewById(R.id.textView57);
               text10.setText(orderDateb);
               orderTimeb =cursor.getString(12);
               TextView text11 = (TextView) findViewById(R.id.textView55);
               text11.setText(orderTimeb);
           }
           TextView text8=(TextView) findViewById(R.id.textView39);
           text8.setText("Success");
           cursor.close();
           db.close();
       }catch(SQLiteException e){
           Toast toast=Toast.makeText(this,"Database unavilable",Toast.LENGTH_SHORT);
           toast.show();
       }

    }


   public void readfromplatformtable(int orderid){

       setContentView(R.layout.activity_final_platform_detail);
       androidx.appcompat.widget.Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar12);
       ActionBar actionBar = getSupportActionBar();
       actionBar.setDisplayHomeAsUpEnabled(true);

       // create a database reference
       SQLiteOpenHelper aquaeasyDatabaseHelper = new AquaeasyDatabaseHelper(this);
       try {
           SQLiteDatabase db = aquaeasyDatabaseHelper.getWritableDatabase();
           Cursor cursor = db.query("PlatformWaterOrder",
                   new String[]{"Id", "Passenger_Name", "PLATFORM_No", "ADN_No", "Quantity", "Total_Price",
                           "Payment_Mode", "Category", "Product_Name", "Mobile_No", "Number_of_Bottle",
                           "Number_of_Packet", "Number_of_Bed","Order_Date","Order_Time"},
                   "Id=?", new String []{Integer.toString((orderid-10000))},
                   null, null, null);
           if (cursor.moveToLast()) {
               idpf = cursor.getInt(0);
               TextView text1 = (TextView) findViewById(R.id.textView25);
               text1.setText(Integer.toString((idpf+10000)));
               pnamepf = cursor.getString(1);
               TextView text2 = (TextView) findViewById(R.id.textView27);
               text2.setText(pnamepf);
               platformnopf = cursor.getInt(2);
               TextView text3 = (TextView) findViewById(R.id.textView29);
               text3.setText(Integer.toString(platformnopf));
               ADNnopf = cursor.getString(3);
               TextView text4 = (TextView) findViewById(R.id.textView31);
               text4.setText(ADNnopf);
               if (!(cursor.isNull(4))) {
                   quantitypf = cursor.getInt(4);
                   text5pf = (TextView) findViewById(R.id.textView33);
                   text5pf.setText(Integer.toString(quantitypf));
                   numpf = 0;
               } else if (!(cursor.isNull(10))) {
                   text32pf = (TextView) findViewById(R.id.textView32);
                   text32pf.setText("No. of Bottle:");
                   noofbottlepf = cursor.getInt(10);
                   text5pf = (TextView) findViewById(R.id.textView33);
                   text5pf.setText(Integer.toString(noofbottlepf));
                   num = 1;
               } else if (!(cursor.isNull(11))) {
                   text32pf = (TextView) findViewById(R.id.textView32);
                   text32pf.setText("No. of Packet:");
                   noofpacketpf = cursor.getInt(11);
                   text5pf = (TextView) findViewById(R.id.textView33);
                   text5pf.setText(Integer.toString(noofpacketpf));
                   numpf = 2;
               } else if (!(cursor.isNull(12))) {
                   text32pf = (TextView) findViewById(R.id.textView32);
                   text32pf.setText("No. of Bed:");
                   noofbedpf = cursor.getInt(12);
                   text5pf = (TextView) findViewById(R.id.textView33);
                   text5pf.setText(Integer.toString(noofbedpf));
                   numpf = 3;
               }
               totalppf = cursor.getInt(5);
               TextView text6 = (TextView) findViewById(R.id.textView35);
               text6.setText(Integer.toString(totalppf));
               modepaypf = cursor.getString(6);
               TextView text7 = (TextView) findViewById(R.id.textView37);
               text7.setText(modepaypf);
               categorypf = cursor.getString(7);
               TextView text8 = (TextView) findViewById(R.id.textView41);
               text8.setText(categorypf);
               brandnamepf = cursor.getString(8);
               TextView text9 = (TextView) findViewById(R.id.textView43);
               text9.setText(brandnamepf);
               mobnopf = cursor.getString(9);
               TextView text10 = (TextView) findViewById(R.id.textView45);
               text10.setText(mobnopf);
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
   }

    public void gotohome(View view){
        Intent intent =new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}

package com.example.punyawork;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MixOrderActivity extends AppCompatActivity {
    public String category;
    public String rate;
    public TextView text1;
    public TextView text2;
    public TextView text3;
    public int value=1;
    public int totalvalue;
    public int rate1;
    public int id;
    public static final String BUS_MIX="BUSMIX";
    public static final String TRAIN_MIX="TRAINMIX";
    public int BUSMIX;
    public int TRAINMIX;
    public Intent intent31;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_order);
        // set the toolbar as activity appbar
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        intent31 = getIntent();

        if(intent31.hasExtra(BUS_MIX)){
        BUSMIX = intent31.getIntExtra(BUS_MIX, 0);}

        if (intent31.hasExtra(TRAIN_MIX)){
            TRAINMIX= intent31.getIntExtra(TRAIN_MIX, 0);
        }

        if (TRAINMIX == 1) {

            // i am going to change the layout of the mixorder activity
            SQLiteOpenHelper mysqlitehelper = new AquaeasyDatabaseHelper(this);
            try {
                SQLiteDatabase db = mysqlitehelper.getWritableDatabase();
                Cursor cursor = db.query("TrainWaterOrder", new String[]{"Id", "Category", "Rate"},
                        null, null, null, null, null);
                if (cursor.moveToLast()) {
                    category = cursor.getString(1);
                    rate = cursor.getString(2);
                    rate1 = Integer.parseInt(rate.replaceAll("[\\D]", ""));
                    totalvalue = rate1;
                }
                if (category.equals("Plastic Bottle") || category.equals("Steel Bottle")) {
                    text1 = (TextView) findViewById(R.id.textView2);
                    text2 = (TextView) findViewById(R.id.textView19);
                    text3 = (TextView) findViewById(R.id.textView21);
                    text1.setText("No. of Bottle");
                    text2.setText(rate);
                    text3.setText(Integer.toString(rate1));
                }
                if (category.equals("Medicine")) {
                    text1 = (TextView) findViewById(R.id.textView2);
                    text2 = (TextView) findViewById(R.id.textView19);
                    text3 = (TextView) findViewById(R.id.textView21);
                    text1.setText("No. of Packet");
                    text2.setText(rate);
                    text3.setText(Integer.toString(rate1));
                }

            } catch (Exception e) {
                Toast toast = Toast.makeText(this, "Database is not access Successfully", Toast.LENGTH_SHORT);
                toast.show();
                ;
            }

        }







        if (BUSMIX == 2) {

        // i am going to change the layout of the mixorder activity
        SQLiteOpenHelper mysqlitehelper = new AquaeasyDatabaseHelper(this);
        try {
            SQLiteDatabase db = mysqlitehelper.getWritableDatabase();
            Cursor cursor = db.query("BusWaterOrder", new String[]{"Id", "Category", "Rate"},
                    null, null, null, null, null);
            if (cursor.moveToLast()) {
                category = cursor.getString(1);
                rate = cursor.getString(2);
                rate1 = Integer.parseInt(rate.replaceAll("[\\D]", ""));
                totalvalue = rate1;
            }
            if (category.equals("Plastic Bottle") || category.equals("Steel Bottle")) {
                text1 = (TextView) findViewById(R.id.textView2);
                text2 = (TextView) findViewById(R.id.textView19);
                text3 = (TextView) findViewById(R.id.textView21);
                text1.setText("No. of Bottle");
                text2.setText(rate);
                text3.setText(Integer.toString(rate1));
            }
            if (category.equals("Medicine")) {
                text1 = (TextView) findViewById(R.id.textView2);
                text2 = (TextView) findViewById(R.id.textView19);
                text3 = (TextView) findViewById(R.id.textView21);
                text1.setText("No. of Packet");
                text2.setText(rate);
                text3.setText(Integer.toString(rate1));
            }

        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Database is not Access Successfully", Toast.LENGTH_SHORT);
            toast.show();
            ;
        }

    }


    }
    public void increaseprice(View view){
        TextView text1=(TextView)findViewById(R.id.editText);
        TextView text2=(TextView)findViewById(R.id.textView21);
        String str= (String) text1.getText();
        value=Integer.parseInt(str);
        value=value+1;
        totalvalue=value*rate1;
        text1.setText(""+value);
        text2.setText("Rs "+totalvalue);}
    public void decreaseprice(View view){
        TextView text1=(TextView)findViewById(R.id.editText);
        TextView text2=(TextView)findViewById(R.id.textView21);
        String str= (String) text1.getText();
        value=Integer.parseInt(str);
        value=value-1;
        totalvalue=value*rate1;
        text1.setText(""+value);
        text2.setText("Rs "+totalvalue);}



    public void mypayment(View view) {
        ContentValues content1 = new ContentValues();
        content1.put("Total_Price", totalvalue);
        if (category.equals("Plastic Bottle") || category.equals("Steel Bottle")) {
            content1.put("Number_of_Bottle", value);
        } else {
            content1.put("Number_of_Packet", value);
        }

        if (TRAINMIX == 1) {
            SQLiteOpenHelper myhelper = new AquaeasyDatabaseHelper(this);
            try {
                SQLiteDatabase db = myhelper.getWritableDatabase();
                Cursor cursor = db.query("TrainWaterOrder",
                        new String[]{"Id"}, null,
                        null, null, null, null);
                if (cursor.moveToLast()) {
                    id = cursor.getInt(0);
                }

                long result = db.update("TrainWaterOrder", content1, "Id= ?",
                        new String[]{Integer.toString(id)});
                if (result == -1) {
                    Toast toast = Toast.makeText(this, "Data is not uploaded in side the TrainWaterOrder Table", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(this, "Data is  uploaded in side the TrainWaterOrder Table", Toast.LENGTH_SHORT);
                    toast.show();
                }
                db.close();
            } catch (Exception e) {
                Toast toast = Toast.makeText(this, "Database is not access Successfully", Toast.LENGTH_SHORT);
                toast.show();
            }


            Intent intent = new Intent(this, PaymentModeActivity.class);
            intent.putExtra(PaymentModeActivity.TPRICE, totalvalue);
            intent.putExtra(PaymentModeActivity.TRAIN_PAY,TRAINMIX);
            startActivity(intent);

        }







        if (BUSMIX == 2) {
            SQLiteOpenHelper myhelper = new AquaeasyDatabaseHelper(this);
            try {
                SQLiteDatabase db = myhelper.getWritableDatabase();
                Cursor cursor = db.query("BusWaterOrder",
                        new String[]{"Id"}, null,
                        null, null, null, null);
                if (cursor.moveToLast()) {
                    id = cursor.getInt(0);
                }

                long result = db.update("BusWaterOrder", content1, "Id= ?",
                        new String[]{Integer.toString(id)});
                if (result == -1) {
                    Toast toast = Toast.makeText(this, "Data is not uploaded in side the BusWaterOrder Table", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(this, "Data is  uploaded in side the BusWaterOrder Table", Toast.LENGTH_SHORT);
                    toast.show();
                }
                db.close();
            } catch (Exception e) {
                Toast toast = Toast.makeText(this, "Database is not access successfully", Toast.LENGTH_SHORT);
                toast.show();
            }


            Intent intent = new Intent(this, PaymentModeActivity.class);
            intent.putExtra(PaymentModeActivity.TPRICE, totalvalue);
            intent.putExtra(PaymentModeActivity.BUS_PAY,BUSMIX);
            startActivity(intent);

        }

    }


}

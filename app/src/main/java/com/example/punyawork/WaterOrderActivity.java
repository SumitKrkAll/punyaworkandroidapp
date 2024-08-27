package com.example.punyawork;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class WaterOrderActivity extends AppCompatActivity {
    public int value=1;
    public int totalvalue=3;
    public int id;
    public static final String BUS_WATER="BUSWATER";
    public static final String TRAIN_WATER="TRAINWATER";
    public Intent intent30;
    public int BUSWATER;
    public int TRAINWATER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_order);
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        intent30=getIntent();
        if(intent30.hasExtra(BUS_WATER)){
        BUSWATER=intent30.getIntExtra(BUS_WATER,0);
        } if (intent30.hasExtra(TRAIN_WATER)){
            TRAINWATER=intent30.getIntExtra(TRAIN_WATER,0);
        }
    }
    public void increaseprice(View view){
        TextView text1=(TextView)findViewById(R.id.editText);
        TextView text2=(TextView)findViewById(R.id.textView21);
         String str= (String) text1.getText();
         value=Integer.parseInt(str);
         value=value+1;
         totalvalue=value*3;
         text1.setText(""+value);
         text2.setText("Rs "+totalvalue);}
    public void decreaseprice(View view){
        TextView text1=(TextView)findViewById(R.id.editText);
        TextView text2=(TextView)findViewById(R.id.textView21);
        String str= (String) text1.getText();
        value=Integer.parseInt(str);
        value=value-1;
        totalvalue=value*3;
        text1.setText(""+value);
        text2.setText("Rs "+totalvalue);}

    public void mypayment(View view) {
        ContentValues content1 = new ContentValues();
        content1.put("Quantity", value);
        content1.put("Total_Price", totalvalue);


        if (TRAINWATER == 1) {
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
                Toast toast = Toast.makeText(this, "Database is not available", Toast.LENGTH_SHORT);
                toast.show();
            }

            // if condition finished at above line

            Intent intent = new Intent(this, PaymentModeActivity.class);
            intent.putExtra(PaymentModeActivity.TPRICE,totalvalue);
            intent.putExtra(PaymentModeActivity.TRAIN,TRAINWATER);
            startActivity(intent);
        }



        if (BUSWATER == 2) {
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
                Toast toast = Toast.makeText(this, "Database is not available", Toast.LENGTH_SHORT);
                toast.show();
            }

            // if condition finished at above line

            Intent intent = new Intent(this, PaymentModeActivity.class);
            intent.putExtra(PaymentModeActivity.TPRICE,totalvalue);
            intent.putExtra(PaymentModeActivity.BUS,BUSWATER);
            startActivity(intent);
        }







    }



}

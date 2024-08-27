package com.example.punyawork;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentModeActivity extends AppCompatActivity {
    public static final String TPRICE="totalprice";
    public static final String TRAIN="TRAINWATER";
    public static final String BUS="BUSWATER";
    public static final String TRAIN_PAY="TRAINMIX";
    public static final String BUS_PAY="BUSMIX";
    public int BUSWATER;
    public int TRAINWATER;
    public int TRAINMIX;
    public int BUSMIX;
    public Intent intent1;
    public RadioButton radioButton;
    public String radioname;
    public int id;
    public ContentValues contentValues;
    public int busno;
    public Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mode);

        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        intent1=getIntent();
        int totalprice=intent1.getIntExtra(TPRICE,0);
        TextView text1=(TextView) findViewById(R.id.textView23);
        text1.setText("Pay Rs "+totalprice +" To Place Order");

        if(intent1.hasExtra(BUS)){
            BUSWATER=intent1.getIntExtra(BUS,0);
        }
        if(intent1.hasExtra(TRAIN)){
            TRAINWATER=intent1.getIntExtra(TRAIN,0);
        }
        if(intent1.hasExtra(BUS_PAY)){
            BUSMIX=intent1.getIntExtra(BUS_PAY,0);
        }
        if(intent1.hasExtra(TRAIN_PAY)){
            TRAINMIX=intent1.getIntExtra(TRAIN_PAY,0);
        }


    }


    public void finalorderdetail(View view){
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        int id= rg.getCheckedRadioButtonId();
        if(id!=-1){
            radioButton=(RadioButton) findViewById(id);
            radioname= radioButton.getText().toString();
        }
        contentValues=new ContentValues();
        contentValues.put("Payment_Mode",radioname);
        SQLiteOpenHelper myopenhelper = new AquaeasyDatabaseHelper(this);

        if((TRAINWATER==1)||(TRAINMIX==1)) {

            try {
                SQLiteDatabase db = myopenhelper.getWritableDatabase();
                cursor = db.query("TrainWaterOrder", new String[]{"Id", "Train_No"}
                        , null, null, null, null, null);
                if (cursor.moveToLast()) {
                    id = cursor.getInt(0);
                }
                long result = db.update("TrainWaterOrder", contentValues, " Id = ?",
                        new String[]{Integer.toString(id)});
                if (result != -1) {
                    Toast toast = Toast.makeText(this,
                            "Data inside the TrainWaterOrder is updated successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (Exception e) {
                Toast toast = Toast.makeText(this,
                        "Database is not access successfully", Toast.LENGTH_SHORT);
                toast.show();
            }

            Intent intent = new Intent(this, FinalOrderDetailActivity.class);
            intent.putExtra(FinalOrderDetailActivity.TRAIN_FINAL,TRAINWATER);
            startActivity(intent);
            Intent intent1 = new Intent(this, DelayedMessageService.class);
            String text1 = getResources().getString(R.string.answer);
            intent1.putExtra(DelayedMessageService.EXTRA_MESSAGE, text1);
            startService(intent1); }

        if((BUSWATER==2)||(BUSMIX==2)) {
            try {
                SQLiteDatabase db = myopenhelper.getWritableDatabase();
                cursor = db.query("BusWaterOrder", new String[]{"Id"}
                        , null, null, null, null, null);
                if (cursor.moveToLast()) {
                    id = cursor.getInt(0);
                }
                long result = db.update("BusWaterOrder", contentValues, " Id = ?",
                        new String[]{Integer.toString(id)});
                if (result != -1) {
                    Toast toast = Toast.makeText(this,
                            "Data inside the BusWaterOrder is updated successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (Exception e) {
                Toast toast = Toast.makeText(this,
                        "Database is not access successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
            Intent intent=new Intent(this,FinalBusOrderDetailActivity.class);
            intent.putExtra(FinalBusOrderDetailActivity.BUS_FINAL,BUSWATER);
            startActivity(intent);
            Intent intent1=new Intent(this,DelayedMessageService.class);
            String text1=getResources().getString(R.string.answer);
            intent1.putExtra(DelayedMessageService.EXTRA_MESSAGE,text1);
            startService(intent1); }
    }

}

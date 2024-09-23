package com.example.punyawork;

import android.content.Intent;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Create_Order extends AppCompatActivity {
    public static  final String WATERID="waterId";
    public static final String MEDICINEID="medicineId";
    public static final String PASTAID="pastaId";
    public static final String PIZZAID="pizzaId";
    public static final String BEDID="pizzaId";
    public Intent intent1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__order);
        Toolbar tool1= (Toolbar) findViewById(R.id.tool1);
        setSupportActionBar(tool1);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        intent1= getIntent();

    }


    public void onClickDone(View view) {
        CharSequence text="Your Order is placed successfully.You will recive your order in 3 minutes" +
                "only.";
        int duration= Snackbar.LENGTH_SHORT;
        Snackbar snackbar= Snackbar.make(findViewById(R.id.coordinator),text,duration);
        snackbar.setAction("REMOVE",new  View.OnClickListener() {
            public void onClick(View view){
                CharSequence text1="Have a Nice Journey";
                int duration1=Toast.LENGTH_SHORT;
                Toast toast= Toast.makeText(Create_Order.this,text1,duration1);
                toast.show();

            }

        });
        snackbar.show();
        Intent intent=new Intent(this,DelayedMessageService.class);
        String text1=getResources().getString(R.string.answer);
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE,text1);
        startService(intent);

    }
    public void myorder2(View view){
        if(intent1.hasExtra(WATERID)) {
            int waterId= intent1.getIntExtra(WATERID,0);
            Intent intent=new Intent(this,OrderActivity.class);
            intent.putExtra(OrderActivity.WATER_ID,waterId);
            startActivity(intent);}
        if(intent1.hasExtra(PIZZAID)) {
            int pizzaId= intent1.getIntExtra(PIZZAID,0);
            Intent intent=new Intent(this,OrderActivity.class);
            intent.putExtra(OrderActivity.PIZZA_ID,pizzaId);
            startActivity(intent);}
        if(intent1.hasExtra(PASTAID)) {
            int pastaId=intent1.getIntExtra(PASTAID,0);
            Intent intent=new Intent(this,OrderActivity.class);
            intent.putExtra(OrderActivity.PASTA_ID,pastaId);
            startActivity(intent);}
        if(intent1.hasExtra(MEDICINEID)) {
            int medicineId=intent1.getIntExtra(MEDICINEID,0);
            Intent intent=new Intent(this,OrderActivity.class);
            intent.putExtra(OrderActivity.MEDICINE_ID,medicineId);
            startActivity(intent);}

}



    public void myorder3(View view){
        if(intent1.hasExtra(WATERID)) {
            int waterId= intent1.getIntExtra(WATERID,0);
            Intent intent=new Intent(this,BusActivity.class);
            intent.putExtra(OrderActivity.WATER_ID,waterId);
            startActivity(intent);}
        if(intent1.hasExtra(PIZZAID)) {
            int pizzaId= intent1.getIntExtra(PIZZAID,0);
            Intent intent=new Intent(this,BusActivity.class);
            intent.putExtra(OrderActivity.PIZZA_ID,pizzaId);
            startActivity(intent);}
        if(intent1.hasExtra(PASTAID)) {
            int pastaId=intent1.getIntExtra(PASTAID,0);
            Intent intent=new Intent(this,BusActivity.class);
            intent.putExtra(OrderActivity.PASTA_ID,pastaId);
            startActivity(intent);}
        if(intent1.hasExtra(MEDICINEID)) {
            int medicineId=intent1.getIntExtra(MEDICINEID,0);
            Intent intent=new Intent(this,BusActivity.class);
            intent.putExtra(OrderActivity.MEDICINE_ID,medicineId);
            startActivity(intent);}


    }


}


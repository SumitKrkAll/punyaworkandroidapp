package com.example.punyawork;

import android.content.Intent;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import android.view.View;


public class MedicineDetailActivity extends AppCompatActivity {
    public static final String EXTRA_Id = "medicineId";
    public int med;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);
        // set the toolbar as activity appbar
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // display the details of the pizza
        int medicineId= (Integer)getIntent().getExtras().get(EXTRA_Id);
        String medicinename= Medicine.medicines[medicineId].getName();
        TextView text1= (TextView) findViewById(R.id.textp);
        text1.setText(medicinename);
        int medicineimage= Medicine.medicines[medicineId].getImageResourceId();
        ImageView image1= (ImageView) findViewById(R.id.medicine_image);
        image1.setImageDrawable(ContextCompat.getDrawable(this,medicineimage));
        image1.setContentDescription(medicinename);
        String pack= Medicine.medicines[medicineId].getPack();
        TextView text6= (TextView) findViewById(R.id.textView6);
        text6.setText(pack);
        String vpack=Medicine.medicines[medicineId].getVpack();
        TextView text7=(TextView) findViewById(R.id.textView7);
        text7.setText(vpack);
        String appl=Medicine.medicines[medicineId].getAppl();
        TextView text8=(TextView) findViewById(R.id.textView8);
        text8.setText(appl);
        String vappl=Medicine.medicines[medicineId].getVappl();
        TextView text9=(TextView) findViewById(R.id.textView9);
        text9.setText(vappl);
        String price=Medicine.medicines[medicineId].getPrice();
        TextView text10=(TextView) findViewById(R.id.textView10);
        text10.setText(price);
        String vprice=Medicine.medicines[medicineId].getVprice();
        TextView text11=(TextView) findViewById(R.id.textView11);
        text11.setText(vprice);
        med=medicineId;
    }
    public void myorder(View view){
        Intent intent=new Intent(this,Create_Order.class);
        intent.putExtra(Create_Order.MEDICINEID,med);
        startActivity(intent);

    }
}

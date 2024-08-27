package com.example.punyawork;

import android.content.Intent;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WaterDetailActivity extends AppCompatActivity {
    public static final String EXTRA_Id = "waterId";
    public int wid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_detail);
        // set the toolbar as activity appbar
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        // display the details of the water
        int waterId= (Integer)getIntent().getExtras().get(EXTRA_Id);
        String watername= Water.waters[waterId].getName();
        TextView text1= (TextView) findViewById(R.id.textp);
        text1.setText(watername);
        int waterimage= Water.waters[waterId].getImageResourceId();
        ImageView image1= (ImageView) findViewById(R.id.water_image);
        image1.setImageDrawable(ContextCompat.getDrawable(this,waterimage));
        image1.setContentDescription(watername);
        String vname= Water.waters[waterId].getVname();
        TextView text6= (TextView) findViewById(R.id.textView6);
        text6.setText(vname);
        String name=Water.waters[waterId].getName();
        TextView text7=(TextView) findViewById(R.id.textView7);
        text7.setText(name);
        String tds=Water.waters[waterId].getTds();
        TextView text8=(TextView) findViewById(R.id.textView8);
        text8.setText(tds);
        String vtds=Water.waters[waterId].getVtds();
        TextView text9=(TextView) findViewById(R.id.textView9);
        text9.setText(vtds);
        String ph=Water.waters[waterId].getPh();
        TextView text10=(TextView) findViewById(R.id.textView10);
        text10.setText(ph);
        String vph=Water.waters[waterId].getVph();
        TextView text11=(TextView) findViewById(R.id.textView11);
        text11.setText(vph);
        String rate= Water.waters[waterId].getRate();
        TextView text12=(TextView) findViewById(R.id.textView12);
        text12.setText(rate);
        String vrate=Water.waters[waterId].getVrate();
        TextView text13=(TextView) findViewById(R.id.textView13);
        text13.setText(vrate);
        String temp=Water.waters[waterId].getTemp();
        TextView text14=(TextView) findViewById(R.id.textView14);
        text14.setText(temp);
        String vtemp=Water.waters[waterId].getVtemp();
        TextView text15=(TextView) findViewById(R.id.textView15);
        text15.setText(vtemp);
        wid=waterId;
    }
    public void myorder(View view){
        Intent intent=new Intent(this,Create_Order.class);
        intent.putExtra(Create_Order.WATERID,wid);
        startActivity(intent);
    }
}

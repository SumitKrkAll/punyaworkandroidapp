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


public class BedDetailActivity extends AppCompatActivity {
    public static final String EXTRA_Id = "bedId";
    public int bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_detail);
        // set the toolbar as activity appbar
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        int bedId= (Integer)getIntent().getExtras().get(EXTRA_Id);
        String bedname= Bed.beds[bedId].getName();
        TextView text1= (TextView) findViewById(R.id.textp);
        text1.setText(bedname);
        int bedimage= Bed.beds[bedId].getImageResourceId();
        ImageView image1= (ImageView) findViewById(R.id.bed_image);
        image1.setImageDrawable(ContextCompat.getDrawable(this,bedimage));
        image1.setContentDescription(bedname);
        String vname= Bed.beds[bedId].getVname();
        TextView text6= (TextView) findViewById(R.id.textView6);
        text6.setText(vname);
        String name=Bed.beds[bedId].getName();
        TextView text7=(TextView) findViewById(R.id.textView7);
        text7.setText(name);
        String categori=Bed.beds[bedId].getCategori();
        TextView text8=(TextView) findViewById(R.id.textView8);
        text8.setText(categori);
        String vcategory=Bed.beds[bedId].getVcategory();
        TextView text9=(TextView) findViewById(R.id.textView9);
        text9.setText(vcategory);
        String timeforuse=Bed.beds[bedId].getTimeforuse();
        TextView text10=(TextView) findViewById(R.id.textView10);
        text10.setText(timeforuse);
        String vtimefortheuse=Bed.beds[bedId].getVtimefortheuse();
        TextView text11=(TextView) findViewById(R.id.textView11);
        text11.setText(vtimefortheuse);
        String durationforuse= Bed.beds[bedId].getDurationforuse();
        TextView text12=(TextView) findViewById(R.id.textView12);
        text12.setText(durationforuse);
        String vdurationforuse=Bed.beds[bedId].getVdurationforuse();
        TextView text13=(TextView) findViewById(R.id.textView13);
        text13.setText(vdurationforuse);
        String servicecharge=Bed.beds[bedId].getServicecharge();
        TextView text14=(TextView) findViewById(R.id.textView14);
        text14.setText(servicecharge);
        String vservicecharge=Bed.beds[bedId].getVservicecharge();
        TextView text15=(TextView) findViewById(R.id.textView15);
        text15.setText(vservicecharge);
        bid=bedId;
    }
    public void myorder(View view){
        Intent intent=new Intent(this,Create_Order.class);
        intent.putExtra(Create_Order.BEDID,bid);
        startActivity(intent);
    }
}

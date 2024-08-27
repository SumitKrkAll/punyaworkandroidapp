package com.example.punyawork;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import android.view.View;


public class PastaDetailActivity extends AppCompatActivity {
    public static final String EXTRA_Id = "pastaId";
    public int pasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta_detail);
        // set the toolbar as activity appbar and add navigation up button
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // display the details of the pasta
        int pastaId= (Integer)getIntent().getExtras().get(EXTRA_Id);
        String pastaname= Pasta.pastas[pastaId].getName();
        TextView text1= (TextView) findViewById(R.id.textp);
        text1.setText(pastaname);
        int pastaimage= Pasta.pastas[pastaId].getImageResourceId();
        ImageView image1= (ImageView) findViewById(R.id.pasta_image);
        image1.setImageDrawable(ContextCompat.getDrawable(this,pastaimage));
        image1.setContentDescription(pastaname);
        String capacity= Pasta.pastas[pastaId].getCap();
        TextView text6= (TextView) findViewById(R.id.textView6);
        text6.setText(capacity);
        String vcapacity=Pasta.pastas[pastaId].getVcap();
        TextView text7=(TextView) findViewById(R.id.textView7);
        text7.setText(vcapacity);
        String weight=Pasta.pastas[pastaId].getWeight();
        TextView text8=(TextView) findViewById(R.id.textView8);
        text8.setText(weight);
        String vweight=Pasta.pastas[pastaId].getVweight();
        TextView text9=(TextView) findViewById(R.id.textView9);
        text9.setText(vweight);
        String price=Pasta.pastas[pastaId].getPrice();
        TextView text10=(TextView) findViewById(R.id.textView10);
        text10.setText(price);
        String vprice=Pasta.pastas[pastaId].getVprice();
        TextView text11=(TextView) findViewById(R.id.textView11);
        text11.setText(vprice);
        pasta=pastaId;
    }
    public void myorder(View view){
        Intent intent=new Intent(this,Create_Order.class);
        intent.putExtra(Create_Order.PASTAID,pasta);
        startActivity(intent); }
}

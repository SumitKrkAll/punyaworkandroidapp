package com.example.punyawork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class BedServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_service);
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        View bedfragmentcontainer=findViewById(R.id.bedfragmentcontainer);
        if(bedfragmentcontainer!=null){
            TopFragment bedFragment=new TopFragment();
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.add(R.id.bedfragmentcontainer,bedFragment);
            ft.commit();
        }
    }
}

package com.example.punyawork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class WaterServiceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_service);
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        View waterfragmentcontainer=findViewById(R.id.waterfragmentcontainer);
        if(waterfragmentcontainer!=null){
            WaterFragment waterFragment=new WaterFragment();
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.add(R.id.waterfragmentcontainer,waterFragment);
            ft.commit();
        }

    }
}

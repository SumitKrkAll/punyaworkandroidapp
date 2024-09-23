package com.example.punyawork;
import androidx.appcompat.app.ActionBar;
import 	androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;

import android.R.*;
public class MainActivity extends AppCompatActivity{
    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // add toolbar at the place of app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // add toolbar at the place of app end


        // attach the sections pager adapter to the viewpager
        SectionsPagerAdapter pagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager=(ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        // attach the sections pager adapter to the viewpager end

        // attach the pager with the  Tablayout
        TabLayout tablayout = (TabLayout) findViewById(R.id.tab);
        tablayout.setupWithViewPager(pager);
        // attach the pager with the  Tablayout end

    }
    // tell the view pager about its pages using fragmentpager adapter
    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        public int getCount(){
            return 5;
        }
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new WaterFragment();

                case 1:
                    return new TopFragment();

                case 2 :
                    return new PizzaFragment();

                case 3:
                    return new PastaFragment();

                case 4:
                    return new StoresFragment();

            }
            return null;
        }

        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    return  getResources().getText(R.string.water_tab);
                case 1:
                    return getResources().getText(R.string.bed_tab);

                case 2:
                    return  getResources().getText(R.string.plasticbottles_tab);

                case 3:
                    return getResources().getText(R.string.steelbottles_tab);

                case 4:
                    return getResources().getText(R.string.medicine_tab);

            }
            return null;
        }}
    // telling a view pager about its pages using fragment pager adpter is completed

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stop, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add3)
        {
            Intent intent1 =  new Intent(this, StopWatechActivity.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);}

    }
}

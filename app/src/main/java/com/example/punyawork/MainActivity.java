package com.example.punyawork;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.punyawork.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add toolbar at the place of app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add toolbar at the place of app end

        // add drawer navigation
        DrawerLayout drawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,
                drawer,toolbar,R.string.nav_open_drawer,R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment= new Fragment();
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame,fragment);
        ft.commit();
        // add drawer navigation end


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

    int add1=0;
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
                    return new TopFragment();
                case 1:
                    return new WaterFragment();

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
                    return getResources().getText(R.string.home_tab);
                case 1:
                    return  getResources().getText(R.string.water_tab);

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.add2);
        shareActionProvider =(ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("want to join me for cold drink");
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.add1){
            Intent intent =  new Intent(this, Create_Order.class);
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setShareActionIntent (String text) {

        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if(shareActionProvider!=null){
            shareActionProvider.setShareIntent(intent);
        }

    }
    public boolean  onNavigationItemSelected(MenuItem item){
        int id=item.getItemId();
        Fragment fragment=null;
        Intent intent=null;

        if(id==R.id.nav_drafts){

            fragment= new DraftsFragment();
        }else if (id== R.id.nav_sent){

            fragment= new SentItemsFragment();
        }else if (id== R.id.nav_trash){

            fragment= new TrashFragment();

        }else if (id== R.id.nav_help){

            intent= new Intent(this,HelpActivity.class);
        }else if (id== R.id.nav_feedback){

            intent = new Intent(this,FeedbackActivity.class);
        }else if (id== R.id.nav_distance){

            intent = new Intent(this,DistanceDisplayActivity.class);
        }else{

            fragment=new InboxFragment();
        }

        if(fragment !=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            ft.commit();
        }else{
            startActivity(intent);
        }
        DrawerLayout drawer= (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onBackPressed(){
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


}
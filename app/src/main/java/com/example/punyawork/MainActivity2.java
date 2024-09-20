package com.example.punyawork;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import 	androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.viewpagerindicator.CirclePageIndicator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import com.viewpagerindicator.CirclePageIndicator;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    ImageView myimage;
    Integer REQUEST_IMAGE_CAPTURE=1,SELECT_FILE=0;
    private ProgressDialog progressBar;
    public FirebaseAuth myauth;
    public String Token;
    public String usermail;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();
    SQLiteOpenHelper aquaeasyDatabasehelper;
    public NavigationView navigationView;
    private ViewPager mpager;
    public static int currentpage=0;
    public static int NUM_PAGES=0;
    public String UserName;
    public String UserEmail;

    private BottomNavigationView bottomNavigationView;
    private static final Integer[]IMAGES={
            R.drawable.ajpg1,R.drawable.ajpg2,R.drawable.ajpg3,R.drawable.ajpeg4,R.drawable.abedjpg1
    };
    private String [] stringarray=new String[] { "WATER IS BEST MEDICINE FOR HEALTH.",
            "BRING ALONG A THERMOS, DON'T USE PLASTIC.",
            "PURE WATER IS THE WORLD'S FIRST AND FOREMOST MEDICINE.",
            "SAVE OUR BEST FRIEND EARTH FROM PLASTIC.",
             "NOW READY TO USE BED AT RAILWAY PLTFORM FOR SLEEP @ Rs 40."};
    private ArrayList<Integer> ImageArray =new ArrayList<Integer>();
    // here is the variables for the ads carousel

    private ViewPager mpagerads;
    public static int currentpageads=0;
    public static int NUM_PAGESads=0;
    private static final Integer[]IMAGESADS={
            R.drawable.adsjpg1,R.drawable.adsjpg2,R.drawable.adsjpg3,R.drawable.adsjpg4,R.drawable.adspng1
    };

    private ArrayList<Integer> ImageArrayads =new ArrayList<Integer>();
    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // add toolbar at the place of app bar
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        // add toolbar at the place of app end
   // here we are creating the token for the user of the our app;
        myauth=FirebaseAuth.getInstance();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (task.isSuccessful()) {
                            // Get the token from the task result
                            String token = task.getResult();
                            // Save the token or use it as needed
                            if(token!=null){
                                saveToken(token);
                            }

                        } else {
                            // Handle the error if the task was not successful
                            Log.e("FirebaseToken", "Failed to get token", task.getException());
                        }
                    }
                });


        // we are going to create notification channel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager=
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID,Constants.CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setDescription("Important Channel");
            mChannel.setLightColor(Color.rgb(255,00,00));
            notificationManager.createNotificationChannel(mChannel);
        }

//we are going to subscribe our channel
        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d("Successfully", msg);

                    }
                });




        SQLiteOpenHelper aquaeasyDatabasehelper=new AquaeasyDatabaseHelper(this);
        try{
            SQLiteDatabase db = aquaeasyDatabasehelper.getWritableDatabase();
            Cursor cursor= db.query("UserDetail",new String[]{"USER_NAME","USER_EMAIL"},null,
                    null,null,null,null);
            if(cursor.moveToLast()){
                UserName=cursor.getString(0);
                UserEmail=cursor.getString(1);
            }

            cursor.close();
            db.close();
        } catch (SQLiteException e){
            Toast toast1= Toast.makeText(MainActivity2.this,
                    "Database is Unavailable", Toast.LENGTH_SHORT);
            toast1.show();
        }
        // add drawer navigation
        DrawerLayout drawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,
                drawer,toolbar12,R.string.nav_open_drawer,R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
         navigationView=(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        TextView text12=(TextView) hView.findViewById(R.id.name);
        text12.setText(UserName);
        TextView text123=(TextView) hView.findViewById(R.id.email);
        text123.setText(UserEmail);
        myimage=(ImageView) hView.findViewById(R.id.myimageview);
        FloatingActionButton fab=(FloatingActionButton) hView.findViewById(R.id.fab);
        hView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntentdetail= new Intent(MainActivity2.this,EditDetailActivity.class);
                startActivity(myIntentdetail);
            }
        });

        aquaeasyDatabasehelper=new DbHelper(this);
        try{
            SQLiteDatabase db= aquaeasyDatabasehelper.getWritableDatabase();
            Cursor cursor= db.query("imagetable",new String[]{"imagename"},null,
                    null,null,null,null);
            if(cursor.moveToLast()){
                byte[] image=cursor.getBlob(0);
                Bitmap bmp= BitmapFactory.decodeByteArray(image,0,image.length);
                myimage.setImageBitmap(bmp);

            }

        }catch (SQLiteException e){
            Toast toast1= Toast.makeText(MainActivity2.this,
                    "Database is Unavailable", Toast.LENGTH_SHORT);
            toast1.show();
        }





        fab.setOnClickListener( new View.OnClickListener (){
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });
        // add drawer navigation end
        init();
        initads();
        bottomNavigationView =(BottomNavigationView) findViewById(R.id.bottomAppBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(menuItem.getItemId()==R.id.add1){
                    Intent intent =  new Intent(MainActivity2.this, MainActivity2.class);
                    startActivity(intent);
                    return true;
                }else if(menuItem.getItemId()==R.id.add2){
                    Intent intent1 =  new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent1);
                    return true;
                }else if(menuItem.getItemId()==R.id.add3){
                    Toast.makeText(MainActivity2.this, "third Bottom", Toast.LENGTH_SHORT).show();
                    return true;
                }else {
                    return false;
                }
            }
        });

        Fragment mainrecyclerfragent= new MainRecyclerFragment();
        if(mainrecyclerfragent!=null){
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container_recycler,mainrecyclerfragent);
            ft.commit();
        }

    }

    public void SelectImage(){
        final  CharSequence[] items={"Camera","Galary","Cancel"};
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle("Set Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(items[i].equals("Camera")){
                    Intent intent1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent1,REQUEST_IMAGE_CAPTURE);
                } else if(items[i].equals("Galary")){
                    Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent,"Select Image"),SELECT_FILE);

                }else if(items[i].equals("Cancel")){
                    dialog.dismiss();

                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==REQUEST_IMAGE_CAPTURE){
                Bundle extras=data.getExtras();
                Bitmap photo=(Bitmap) extras.get("data");
                setProgressBar();
                myimage.setImageBitmap(photo);
                myimage.setDrawingCacheEnabled(true);
                myimage.buildDrawingCache();
                Bitmap bitmap=myimage.getDrawingCache();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] data1= baos.toByteArray();
                aquaeasyDatabasehelper=new DbHelper(this);
                try{
                    SQLiteDatabase db=aquaeasyDatabasehelper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("imagename",   data1);
                    long result=db.insert( "imagetable", null, cv );
                    if(result!=-1){
                        Toast.makeText(this,"Image is save to the database",Toast.LENGTH_SHORT).show();
                    }
                }catch (SQLiteException e){
                    Toast toast1= Toast.makeText(MainActivity2.this,
                            "Database is Unavailable", Toast.LENGTH_SHORT);
                    toast1.show();
                }








            }else if (requestCode==SELECT_FILE){
                Uri selectedImageUri =data.getData();
                setProgressBar();
                myimage.setImageURI(selectedImageUri);
                myimage.setDrawingCacheEnabled(true);
                myimage.buildDrawingCache();
                Bitmap bitmap=myimage.getDrawingCache();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] data1= baos.toByteArray();
                aquaeasyDatabasehelper=new DbHelper(this);
                try{
                    SQLiteDatabase db=aquaeasyDatabasehelper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("imagename",   data1);
                    long result=db.insert( "imagetable", null, cv );
                    if(result!=-1){
                        Toast.makeText(this,"Image is save to the database",Toast.LENGTH_SHORT).show();
                    }
                }catch (SQLiteException e){
                    Toast toast1= Toast.makeText(MainActivity2.this,
                            "Database is Unavailable", Toast.LENGTH_SHORT);
                    toast1.show();
                }







            }
        }
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.add1){
            Intent intent =  new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId()==R.id.add3){
            Intent intent1 =  new Intent(this, StopWatechActivity.class);
            startActivity(intent1);
            return true;
        }else if(item.getItemId()==R.id.add2){
            setShareActionIntent("want to join for cold drink");
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }

    }

    private void setShareActionIntent (String text) {
        Log.v("tag","Welcome Sumit to Share Action");
        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        Intent chooser=Intent.createChooser(intent,"Share via");
        startActivity(chooser);

    }

    public boolean  onNavigationItemSelected(MenuItem item){
        int id=item.getItemId();
        Fragment fragment=null;
        Intent intent=null;

        if(id==R.id.nav_sent){
            fragment= new SentItemsFragment();
        }else if(id==R.id.nav_inbox){
            fragment= new InboxFragment();
        }else if(id==R.id.nav_help){
            intent= new Intent(this,HelpActivity.class);
        }else if(id==R.id.nav_feedback){
            intent = new Intent(this,FeedbackActivity.class);
        }else if(id==R.id.nav_distance){
            intent= new Intent(this,DistanceDisplayActivity.class);
        }else if(id==R.id.nav_location){
            intent= new Intent(this,YourLocation1Activity.class);
        }else if(id==R.id.nav_signup){
            intent= new Intent(this,SelectLoginAndSignupActivity.class);
        }else if(id==R.id.nav_shop){
            intent= new Intent(this,Order_History.class);
        }else if(id==R.id.nav_privacy){
            intent= new Intent(this,PrivacyActivity.class);
        }

        if(fragment !=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,fragment);
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

    private void init(){
        for(int i=0;i<IMAGES.length;i++){
            ImageArray.add(IMAGES[i]);

        }
           mpager=(ViewPager)findViewById(R.id.myviewpager);
        mpager.setAdapter(new SliddingImage_adapter(MainActivity2.this,ImageArray,stringarray));
        CirclePageIndicator indicator=(CirclePageIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(mpager);
        final float density = getResources().getDisplayMetrics().density;
        // set the radius of the circle indicator
        indicator.setRadius(5 * density);
        NUM_PAGES=IMAGES.length;
        // auto start of the view pager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(currentpage==NUM_PAGES){
                    currentpage=0;
                }mpager.setCurrentItem(currentpage++,true);
            }
        };
        Timer swiptimer= new Timer();
        swiptimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);

            }
        },3000,3000);
        // pager listener over indicators
//        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentpage=position;
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int pos) {
//
//            }
//        });

    }
    // here is the ads moving craousal
    private void initads(){
        for(int i=0;i<IMAGESADS.length;i++){
            ImageArrayads.add(IMAGESADS[i]);

        }
        mpagerads=(ViewPager)findViewById(R.id.myviewpagerads);
        mpagerads.setAdapter(new SliddingAds_adapter(MainActivity2.this,ImageArrayads));
        CirclePageIndicator indicatorads=(CirclePageIndicator) findViewById(R.id.indicatorads);
//        indicatorads.setViewPager(mpagerads);
        final float density = getResources().getDisplayMetrics().density;
        // set the radius of the circle indicator
        indicatorads.setRadius(5 * density);
        NUM_PAGESads=IMAGESADS.length;
        // auto start of the view pager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(currentpageads==NUM_PAGESads){
                    currentpageads=0;
                }
                mpagerads.setCurrentItem(currentpageads++,true);
            }
        };
        Timer swiptimer= new Timer();
        swiptimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);

            }
        },3000,3000);
        // pager listener over indicators
//        indicatorads.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentpageads=position;
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int pos) {
//
//            }
//        });

    }

    public void setProgressBar(){
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please wait...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBarStatus < 100){
                    progressBarStatus += 30;

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarbHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }
                if (progressBarStatus >= 100) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.dismiss();
                }

            }
        }).start();
    }
// this method is used to save the token in the firebase database
    public void saveToken( String Token){

        if(myauth!=null){
            usermail=myauth.getCurrentUser().getEmail();
            UsersDetail usersDetail=new UsersDetail(usermail,Token);

            DatabaseReference mydatabase= FirebaseDatabase.getInstance().getReference("USERS");
            mydatabase.child(myauth.getCurrentUser().getUid())
                    .setValue(usersDetail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    });
        }


    }
}

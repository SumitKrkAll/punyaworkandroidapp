package com.example.punyawork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.os.Bundle;

public class Order_History extends AppCompatActivity {

    RecyclerView Recycler;
    RecyclerView.LayoutManager layoutManager;
    Recycleradapter adapter;
    ArrayList<Contacts> arrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__history);
        androidx.appcompat.widget.Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Recycler=(RecyclerView)findViewById(R.id.recycler);
        layoutManager=new LinearLayoutManager(this);
        Recycler.setLayoutManager(layoutManager);
        Recycler.setHasFixedSize(true);
        adapter=new Recycleradapter(arrayList);
        Recycler.setAdapter(adapter);
        adapter.setListener(new Recycleradapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent= new Intent(Order_History.this,MyOrderHistoryDetail.class);
                intent.putExtra(MyOrderHistoryDetail.ORDER_ID,position);
                startActivity(intent);
            }
        });
        readinfofromalllocaldatabase();
    }



    public void readinfofromalllocaldatabase(){
        arrayList.clear();
        SQLiteOpenHelper aquaeasyDatabaseHelper=new AquaeasyDatabaseHelper(this);
        SQLiteDatabase db = aquaeasyDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query("TrainWaterOrder",
                new String[]{"Id","Category","Sync_Status","Order_Date"},
                null, null,
                null, null, null);

        while (cursor.moveToNext()){
            String Categoryname=cursor.getString(1);
            int Syncstatus=cursor.getInt(2);
            int OrderId=100+cursor.getInt(0);
            String Order_Date=cursor.getString(3);
            arrayList.add(new Contacts(OrderId,Syncstatus,Categoryname,Order_Date));
        }

        Cursor cursor1 = db.query("BusWaterOrder",
                new String[]{"Id","Category","Sync_Status","Order_Date"},
                null, null,
                null, null, null);
        while (cursor1.moveToNext()){
            String Categoryname=cursor1.getString(1);
            int Syncstatus=cursor1.getInt(2);
            int OrderId=1000+cursor1.getInt(0);
            String Order_Date=cursor1.getString(3);
            arrayList.add(new Contacts(OrderId,Syncstatus,Categoryname,Order_Date));
        }
        Cursor cursor2 = db.query("PlatformWaterOrder",
                new String[]{"Id","Category","Sync_Status","Order_Date"},
                null, null,
                null, null, null);
        while (cursor2.moveToNext()){
            String Categoryname=cursor2.getString(1);
            int Syncstatus=cursor2.getInt(2);
            int OrderId=10000+cursor2.getInt(0);
            String Order_Date=cursor2.getString(3);
            arrayList.add(new Contacts(OrderId,Syncstatus,Categoryname,Order_Date));
        }


        adapter.notifyDataSetChanged();
        cursor.close();
        aquaeasyDatabaseHelper.close();
    }

    public boolean checknetworkconnection(){
        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }


    }


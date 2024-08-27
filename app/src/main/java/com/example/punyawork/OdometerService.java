package com.example.punyawork;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.location.LocationManager;
import android.content.Context;
import android.location.Criteria;

import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;

public class OdometerService extends Service {
    private final IBinder binder=new OdometerBinder();
    private LocationListener listner;
    public static final String PERMISSION_STRING=
            Manifest.permission.ACCESS_FINE_LOCATION;
    private LocationManager locationManager;
    private static float distanceinmeter;
    private static Location lastlocation=null;


    @Override
    public IBinder onBind(Intent intent) {
        return  binder;
    }
    public class OdometerBinder extends Binder {
        OdometerService getOdometer(){
            return  OdometerService.this;
        }
    }
    public float getDistance(){
        return distanceinmeter;
    }
    public void onCreate(){
        super.onCreate();
        listner=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(lastlocation==null){
                    lastlocation=location;
                }
                distanceinmeter  +=location.distanceTo(lastlocation);
                lastlocation=location;


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(this,PERMISSION_STRING)==PackageManager.PERMISSION_GRANTED){
            String locationprovider=locationManager.getBestProvider(new Criteria(),true);
            if(locationprovider !=null){
                locationManager.requestLocationUpdates(locationprovider,1000, 1,listner);
            }
        }

    }
    public  void onDestroy(){
        super.onDestroy();
        if(locationManager !=null && listner !=null){
            if(ContextCompat.checkSelfPermission(this,PERMISSION_STRING)==PackageManager.PERMISSION_GRANTED){
                locationManager.removeUpdates(listner);
            }
            locationManager=null;
            listner=null;
        }
    }
}

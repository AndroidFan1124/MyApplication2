package com.example.myapplication.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MyService extends Service implements LocationListener {

    public Location location;
    public double longitude;
    public double latitude;


    //The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters

    //The minimum time beetwen updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 0;//1000 * 60 * 1; // 1 minute

    private final static boolean forceNetwork = false;

    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private boolean locationServiceAvailable;

    private static MyService instance = null;

    LocationManager locationManager = null;

//    public static MyService getLocationService(Context context) {
//        Log.d("wmy","public static MyService()");
//        if (instance == null) {
//            instance = new MyService(context);
//        }
//        return instance;
//    }

    public MyService() {

    }
    Context context;
    public MyService(Context context) {
        Log.d("wmy","private MyService(Context context)");
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Log.d("wmy", "onCreate");
    }

    /**
     * Sets up location service after permissions is granted
     */
    @TargetApi(23)
    private void initLocationService(Context context) {

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        try {
            this.longitude = 0.0;
            this.latitude = 0.0;
            this.locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
            // Get GPS and network status
            this.isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            this.isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Log.d("wmy","isGPSEnabled:"+isGPSEnabled+",isNetworkEnabled:"+isNetworkEnabled);
            if (forceNetwork){
                isGPSEnabled = false;
            }
            if (!isNetworkEnabled && !isGPSEnabled) {
                // cannot get location
                this.locationServiceAvailable = false;
            }
            //false  true
            //else
            {
                this.locationServiceAvailable = true;
                Log.d("wmy","isNetworkEnabled:"+isNetworkEnabled);
                if (isNetworkEnabled) {
                    Toast.makeText(getApplicationContext(),"isNetworkEnabled:"+isNetworkEnabled,Toast.LENGTH_SHORT);
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        Log.d("wmy","net2--location:"+location);
                        Toast.makeText(getApplicationContext(),"NET---location:"+location,Toast.LENGTH_SHORT);
                        //更新坐标
                       if(location!=null)
                           updateCoordinates(location);
                    }
                }  if (isGPSEnabled) {
                Toast.makeText(getApplicationContext(),"isGPSEnabled:"+isGPSEnabled,Toast.LENGTH_SHORT);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        Toast.makeText(getApplicationContext(),"GPS---location:"+location,Toast.LENGTH_SHORT);
//                        Log.d("wmy",isGPSEnabled+"--isGPSEnabled--isNetworkEnabled---location:"+location);
                        //更新坐标
                        if(location!=null)
                            updateCoordinates(location);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateCoordinates(Location location) {
        Log.d("wmy","location"+location);
        double latitude = location.getLatitude();//纬度
        double longitude = location.getLongitude();//经度onStartCommand
        Log.d("wmy", "纬度：" + latitude + ",经度：" + longitude);
        Toast.makeText(context, "纬度：" + latitude + ",经度：" + longitude,Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("wmy", "onStartCommand");
        Log.d("wmy","context:"+context);
        initLocationService(context);
//        if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
//        } else if (locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, null);
//        } else {
//            Toast.makeText(this, "无法定位，定位失败", Toast.LENGTH_SHORT).show();
//        }
//        Call requires permission which may be rejected by user: code should explicitly check to see if permission is available (with `checkPermission`) or explicitly handle a potential `SecurityException`


        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("wmy", "onLocationChanged");
        //通知Activity
//        locationManager
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

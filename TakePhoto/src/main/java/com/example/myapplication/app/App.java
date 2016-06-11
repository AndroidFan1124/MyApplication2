package com.example.myapplication.app;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.example.myapplication.service.LocationService;

import org.lasque.tusdk.core.TuSdk;

/**
 * Created by Administrator on 2016/5/24.
 */
public class App extends Application{

    public LocationService locationService;
    public Vibrator mVibrator;
    static App app = null;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        // 开发ID (请前往 http://tusdk.com 获取您的 APP 开发秘钥)
        TuSdk.init(this.getApplicationContext(), "12339ae72d127b1c-00-mzrcp1");
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
//        SDKInitializer.initialize(getApplicationContext());
    }

    public static App getInstance(){
        return app;
    }
}

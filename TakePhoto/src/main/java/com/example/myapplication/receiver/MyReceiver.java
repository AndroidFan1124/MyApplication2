package com.example.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.myapplication.service.MyLocationService;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MyReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = "android.hardware.action.NEW_PICTURE";
        if (!intent.getAction().equals(action)) return;
        Log.d("wmy","收到拍照广播");
//        Intent service_intent = new Intent(context, MyService.class);
//        context.startService(service_intent);

        MyLocationService s = new MyLocationService(context);
        Intent i = new Intent(context,s.getClass());
        context.startService(i);
    }
}

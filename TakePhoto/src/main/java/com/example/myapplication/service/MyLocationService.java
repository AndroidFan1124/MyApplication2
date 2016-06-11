package com.example.myapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.example.myapplication.app.App;
import com.example.myapplication.entity.Place;

/**
 * Created by Administrator on 2016/5/24.
 */

public class MyLocationService extends Service {

    Context context;
    Place place = new Place();
    final String TYPE="成功";

    private LocationService locationService;

    public MyLocationService(){

    }

    public MyLocationService(Context context){
        Log.d("wmy","MyLocationService");
        this.context = context;
//        place = new Place();
    }

    /**
     * 次一次创建服务时执行的方法
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 每次启动服务时调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("wmy","onStartCommand");
        initData();
        return super.onStartCommand(intent, flags, startId);
    }

    private void initData() {
        Log.d("wmy","initData");
        locationService = ((App) getApplication()).locationService;
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();


    }


    /*****
     * copy funtion to you project
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            Log.d("wmy","onReceiveLocation");
            Log.d("wmy","location:"+location);
            Log.d("wmy","location.getLocType():"+location.getLocType());
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                Log.d("wmy","进入IF");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                place.setTime(location.getTime());

                place.setLatitude(location.getLatitude());

                place.setLontitude(location.getLongitude());

                place.setCountry(location.getCountry());

                place.setCity(location.getCity());

                place.setDistrict(location.getDistrict());

                place.setStreet(location.getStreet());

                place.setAddr(location.getAddrStr());

                place.setDescride(location.getLocationDescribe());

                Log.d("wmy","走到这了1111");

                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    Log.d("wmy","走到这了2222");
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        place.setPoi(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果

                    place.setLocationType("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    place.setLocationType("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    place.setLocationType("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    place.setLocationType("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    place.setLocationType("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    place.setLocationType("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
            }
            Log.d("wmy","place"+place.toString());

            if(place.getLocationType().contains(TYPE)){
                stopSelf();
            }
        }



    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onDestroy();
    }
}

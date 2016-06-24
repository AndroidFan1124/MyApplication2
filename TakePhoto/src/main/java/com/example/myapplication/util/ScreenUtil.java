package com.example.myapplication.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.example.myapplication.app.App;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ScreenUtil {
    static  Context context = null;
    public static int getScrrenWidth(){
        context = App.getInstance();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if(manager!=null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(outMetrics);
            return outMetrics.widthPixels;
        }else{
            Log.d("wmy","manager是空");
        }
       return 0;
    }

    public static int getScreenHeight(){
        WindowManager manager = (WindowManager) App.getInstance().getSystemService(Context.WINDOW_SERVICE);
       DisplayMetrics outMetrics = new DisplayMetrics();

        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}

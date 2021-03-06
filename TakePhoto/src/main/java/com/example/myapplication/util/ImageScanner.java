package com.example.myapplication.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

/**
 * 利用ContentProvider扫描手机中的图片
 * Created by Administrator on 2016/5/17.
 */
public class ImageScanner {
    private Context context;
    public ImageScanner(Context context) {
        this.context = context;

    }

    public void scanImages(final ScanCompleteCallBack callback) {
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                callback.scanComplete((Cursor) msg.obj);
            }
        };
//        Error:Error converting bytecode to dex:
//        Cause: com.android.dex.DexException: Multiple dex files define Landroid/support/v4/accessibilityservice/AccessibilityServiceInfoCompat$AccessibilityServiceInfoVersionImpl;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = context.getContentResolver();

                Cursor mCursor  = mContentResolver.query(mImageUri,null,null,null,null);

                //利用Handler通知调用线程
                Message msg = mHandler.obtainMessage();
                msg.obj = mCursor;
                Log.d("wmy","msg.obj:"+msg.obj);
                mHandler.sendMessage(msg);

            }
        }).start();

    }

    public static interface ScanCompleteCallBack {
        public void scanComplete(Cursor cursor);
    }
}

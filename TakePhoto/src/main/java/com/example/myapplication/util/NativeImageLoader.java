package com.example.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 本地图片加载器
 * 采用的是异步解析本地图片，单例模式利用getInstance()获取NativeImageLoader实例
 * 调用loadNativeImage()方法加载本地图片，此类可作为一个加载本地图片的工具类
 *
 * @blog http://blog.csdn.net/xiaanming
 *
 * @author wmy
 *
 */
public class NativeImageLoader {
    static  NativeImageLoader mInstance = new NativeImageLoader();
    private LruCache<String,Bitmap> mMemoryCache ;
    private ExecutorService mImageThreadPool = Executors.newFixedThreadPool(1);
    private NativeImageLoader (){
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory());
        //用最大内存的1/4来存储图片
        final int cacheSize = maxMemory /4;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            //获取每张图片的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    /**
     * 获取NativeImageLoaders实例
     * @return
     */
    public static NativeImageLoader getInstance(){
        return mInstance;
    }

    public Bitmap loadNativeImage(final String path,final NativeImageCallBack callback){
        return loadNativeImage(path,null,callback);
    }

    /**
     * 此方法来加载本地图片，这里的mPoint是用来封装ImageView的宽和高，我们会根据ImageView控件的大小来裁剪Bitmap
     * 如果你不想裁剪图片，调用loadNativeImage(final String path, final NativeImageCallBack mCallBack)来加载
     * @param path
     * @param point
     * @param callback
     * @return
     */
    public Bitmap loadNativeImage(final String path,final Point point ,final NativeImageCallBack callback){
        //先获取内存中的BItmap
        Bitmap bitmap = getBitmapFromMemCache(path);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                callback.onImageLoader((Bitmap) msg.obj,path);
            }
        };
        //若该Bitmap不在内存缓存中，则启用线程去加载本地的图片，并将Bitmap加入到mMemoryCache中
        if(bitmap == null){
            mImageThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //先获取图片的缩略图
                    Bitmap mBitmap = decodeThumbBitmapForFile(path,point==null ? 0:point.x,point == null ? 0 :point.y);
                    Message msg =handler .obtainMessage();
                    msg.obj = mBitmap;
                    handler.sendMessage(msg);
                    //将图片加入到内存缓存
                    addBitmapToMemoryCache(path,mBitmap);

                }


            });
        }
        return bitmap;
    }
private Bitmap decodeThumbBitmapForFile(String path, int viewWidth, int viewHeight) {

    BitmapFactory.Options options = new BitmapFactory.Options();
    //设置为true,表示解析Bitmap对象，该对象不占内存
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(path,options);
    //设置缩放比例
    options.inSampleSize = computeScale(options,viewWidth,viewHeight);

    //设置为false,解析Bitmap对象加入到内存中
    options.inJustDecodeBounds = false;

    return BitmapFactory.decodeFile(path, options);
}

    /**
     * 根据View（主要是ImageView）的宽和高来计算Bitmap缩放比例。默认不缩放
     * @param options
     * @param viewWidth
     * @param viewHeight
     * @return
     */
    private int computeScale(BitmapFactory.Options options, int viewWidth, int viewHeight) {
        int inSampleSize = 1;
        if(viewWidth ==0 || viewWidth ==0){
            return inSampleSize;
        }
        int bitmapWidth = options.outWidth;
        int bitmapHeight = options.outHeight;

        //假如Bitmap的宽度和高度大于我们设定的图片的的View的宽和高，则设置缩放比例
        if(bitmapWidth > viewWidth || bitmapHeight > viewWidth){
            int widthScale = Math.round((float) bitmapWidth / (float) viewWidth);
            int heightScale = Math.round((float) bitmapHeight / (float) viewWidth);

            //为了保证图片不缩放变形，我们取宽高比例最小的那个
            inSampleSize = widthScale < heightScale ? widthScale : heightScale;
        }
return inSampleSize;
    }

    /**
     * 根据path获取内存中的图片
     * @param path
     * @return
     */
    private Bitmap getBitmapFromMemCache(String path) {
        Bitmap b =  mMemoryCache.get(path);
        if(b!=null){
            Log.d("wmy","get bitmap from cache");
        }
        return b;
    }

    private void addBitmapToMemoryCache(String path,Bitmap b){
        if(getBitmapFromMemCache(path)==null && b!=null){
            mMemoryCache.put(path,b);
        }
    }

    public interface NativeImageCallBack{
        /**
         * 当子线程加载完了图片，将Bitmap和图片路径回调在此方法中
         * @param bitmap
         * @param path
         */
        public void onImageLoader(Bitmap bitmap, String path);
    }
}

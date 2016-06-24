package com.example.myapplication.util;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.example.myapplication.app.App;
import com.example.myapplication.entity.AppInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/14.
 */
public class PackageUtil {

    public static List<File> fileList = new ArrayList<File>();
    public static Map<String,  List<File>> mapList = new HashMap<String,  List<File>>();

    public static String[] indexs = {"bmp", "pcx", "tiff", "gif", "jpeg", "jpg",
            "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo",
            "eps", "png", "hdri", "ai", "raw"};

    public static Map<String,  List<File>> getAllAppPic() {
        File file;
        //检测SD卡是否存在
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            file = Environment.getExternalStorageDirectory();
            // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
            getAllFiles(file);
        }

        return mapList;
    }

    static String rootPath = "";

    private static void getAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory() && f.canRead()) {
//                    getAllFiles(f);
                    if (hasPicture(f)) {
                        rootPath = f.getName();
                        mapList.put(rootPath,fileList);
                        Log.d("wmy","rootPath:"+rootPath);
                    }else{
//
                    }
//                    directList.add(file);
//                    getAllFiles(f);
                } else if (f.isFile() && f.canRead()) {
//                    String filename =f.getName().trim().toLowerCase();
//                    // 判断是否为MP4结尾
//                    if (filename.endsWith(".jpg")) {
//                        .add(filename);
//                    }
                    if (isPicture(f)) {
                        fileList.add(f);
                    }
//                        fileList.add(file);
                }
            }
        }
    }

    /**
     * 判断文件夹下是否有图片,深度遍历
     *
     * @param file
     * @return
     */
    private static boolean hasPicture(File file) {
        File[] files = file.listFiles();
        if (files.length > 0) {
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    if (hasPicture(file2))
                        return true;
                } else {
                    if (isPicture(file2)) {
                        fileList.add(file2);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断文件是否是图片文件
     *
     * @param file
     * @return
     */
    private static boolean isPicture(File file) {
        int start = file.getName().lastIndexOf(".");
        int end = file.getName().length();
        if (start != -1) {
            String indexName = file.getName().substring(start + 1, end);
            if (inIndexs(indexName)) {
                return true;
            }
        } else {
            Log.i("myTag", "没有后缀名的文件");
        }
        return false;
    }

    /**
     * 判断后缀名是否是图片后缀
     * @param index
     * @return
     */
    private static boolean inIndexs(String index) {
        for (String string : indexs) {
            if (index.equals(string)) {
                return true;
            }
        }
        return false;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void getAppInfo() {
        App app = App.getInstance();
//        PackageManager pm = app.getPackageManager();
        File file = app.getCacheDir();
        Log.d("wmy", "file--->" + file.getName());
//        app.getExternalFilesDirs("");
//        File[] files = pm.get();
//        for (int i = 0; i < files.length; i++) {
//            Log.d("wmy", "files--->" + files[i].getName() + "-path-->" + files[i].getPath());
//        }

        List<PackageInfo> appList = getAllApps(app);
        queryAppInfo(app);
    }

    /**
     * 查询手机内非系统应用
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getAllApps(Context context) {
        Log.d("wmy", "getAllApps()----------------------------------------");
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = context.getPackageManager();
        //获取手机内所有应用
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            Log.d("wmy", "getAllApps()--->" + pak);
            //判断是否为非系统预装的应用程序
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                // customs applications
                apps.add(pak);
            }
        }
        return apps;
    }


    // 获得所有启动Activity的信息，类似于Launch界面
    public static void queryAppInfo(Context app) {
        Log.d("wmy", "queryAppInfo()----------------------------------------");
        List<AppInfo> mlistAppInfo = new ArrayList<AppInfo>();
        PackageManager pm = app.getPackageManager(); // 获得PackageManager对象
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 通过查询，获得所有ResolveInfo对象.
        List<ResolveInfo> resolveInfos = pm
                .queryIntentActivities(mainIntent, PackageManager.MATCH_DEFAULT_ONLY);
        // 调用系统排序 ， 根据name排序
        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));
        if (mlistAppInfo != null) {
            mlistAppInfo.clear();
            for (ResolveInfo reInfo : resolveInfos) {
                String activityName = reInfo.activityInfo.name; // 获得该应用程序的启动Activity的name
                String pkgName = reInfo.activityInfo.packageName; // 获得应用程序的包名
                String appLabel = (String) reInfo.loadLabel(pm); // 获得应用程序的Label
                Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
                // 为应用程序的启动Activity 准备Intent
                Intent launchIntent = new Intent();
                launchIntent.setComponent(new ComponentName(pkgName,
                        activityName));
                // 创建一个AppInfo对象，并赋值
                AppInfo appInfo = new AppInfo();
                appInfo.setAppLabel(appLabel);
                appInfo.setPkgName(pkgName);

                mlistAppInfo.add(appInfo); // 添加至列表中
                Log.d("wmy", appLabel + " activityName---" + activityName
                        + " pkgName---" + pkgName);
            }
        }
    }
}


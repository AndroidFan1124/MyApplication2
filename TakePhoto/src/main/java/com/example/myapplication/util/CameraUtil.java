package com.example.myapplication.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by wangmengyan on 2016/6/4.
 */
public class CameraUtil {

   /* public static  String MOUNT_POINT = "";
    public static  String DCIM = "";
    public static  String DIRECTORY = "";
    public static void updateDefaultDirectory(Activity activity, boolean stillCapture) {
//        String defaultPath =
// StorageManager.getDefaultPath();
        MOUNT_POINT = defaultPath;
        Log.i("wmy", "Write default path =" + defaultPath);

        if (Util.CU) {
            DCIM = stillCapture ? (MOUNT_POINT + "/Photo") : (MOUNT_POINT + "/Video");
            DIRECTORY = DCIM;
        } else {
            DCIM = MOUNT_POINT + "/DCIM";
            DIRECTORY = DCIM + "/Camera";
        }
        BUCKET_ID = String.valueOf(DIRECTORY.toLowerCase().hashCode());
//        中的DIRECTORY = DCIM +”/Camera”改为DIRECTORY = 您想要的目录名;
//        做完以上修改后, 拍的照片就会默认保存在您定义的目录下.
    }*/

    public static boolean  getSdState(){
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public static String getDefaultPath(){
        if(getSdState()){
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            Log.d("wmy",path.getPath());
        }

        return "";
    }
}

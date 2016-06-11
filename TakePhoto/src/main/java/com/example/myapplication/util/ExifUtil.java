package com.example.myapplication.util;


import android.media.ExifInterface;

import com.example.myapplication.entity.Picture;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/31.
 */
public class ExifUtil {
    public Picture getAllPictureInfo(String path){
        try {
            Picture pic = new Picture();
            ExifInterface inter = new ExifInterface(path);
            /**
             *
             private String dateTime ;//日期时间
             private String tag_aperture;//光圈
             private String falsh ;//闪光灯
             private String gpsLatitude ;//纬度
             private String gpsLatitudeRef ;//纬度参考
             private String gpsLongitude ;//经度
             private String gpsLongitudeRef ;//经度参考
             private String tag_iso ;//ISO
             private String make ;//图片制造商
             private String tag_model ;//设备型号
             private String orientation ;//方向
             private String whitebalance ;//白平衡
             */
            pic.setDateTime(inter.getAttribute(ExifInterface.TAG_DATETIME));
            pic.setTag_aperture(inter.getAttribute(ExifInterface.TAG_APERTURE));

            pic.setFalsh(inter.getAttribute(ExifInterface.TAG_FLASH));
            pic.setGpsLatitude(inter.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
            ;
            pic.setGpsLatitudeRef(inter.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF));
            pic.setGpsLongitude(inter.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));

            pic.setGpsLongitudeRef(inter.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF));
            pic.setTag_iso(inter.getAttribute(ExifInterface.TAG_ISO));

            pic.setMake(inter.getAttribute(ExifInterface.TAG_MAKE));
            pic.setTag_model(inter.getAttribute(ExifInterface.TAG_MODEL));

            pic.setOrientation(inter.getAttribute(ExifInterface.TAG_ORIENTATION));
            pic.setWhitebalance(inter.getAttribute(ExifInterface.TAG_WHITE_BALANCE));


            return pic;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}

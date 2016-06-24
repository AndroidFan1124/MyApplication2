package com.example.myapplication.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.SampleBase;
import com.example.myapplication.SampleGroup;
import com.example.myapplication.activity.ImageActivity;
import com.example.myapplication.activity.InfoActivity;
import com.example.myapplication.entity.Picture;
import com.example.myapplication.util.PopupWindowUtil;
import com.example.myapplication.view.IImageView;

import org.lasque.tusdk.TuSdkGeeV1;
import org.lasque.tusdk.core.TuSdkResult;
import org.lasque.tusdk.core.utils.TLog;
import org.lasque.tusdk.core.utils.image.BitmapHelper;
import org.lasque.tusdk.impl.activity.TuFragment;
import org.lasque.tusdk.impl.components.TuEditMultipleComponent;
import org.lasque.tusdk.modules.components.TuSdkComponent;
import org.lasque.tusdk.modules.components.TuSdkHelperComponent;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/1.
 */
public class ImagePresenter extends SampleBase {
    Activity mContext;
    IImageView view;
    TextView tv_pic_info;
    TextView tv_beauty_pic;
    String filePath = "";
//    Picture picture;

    public ImagePresenter(IImageView view, ImageActivity mContext) {
        super(SampleGroup.GroupType.SuiteSample, R.string.sample_EditMultipleComponent);
        this.mContext = mContext;
        this.view = view;
    }

    public void showImage(Picture item) {
        filePath = item.getPath();
        Log.d("wmy", "path:" + filePath);
//        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//        Log.d("wmy", "bitmap:" + bitmap);
//        view.getImageDetail().setImageBitmap(bitmap);
        Glide.with(mContext)
                .load(filePath)
                .into(view.getImageDetail());
    }

    public void animationDown(View view,final Picture picture) {
//        this.picture = picture;
        PopupWindowUtil util = new PopupWindowUtil();
        View v = util.showPopupInfo(R.layout.popup_image, mContext);
        tv_beauty_pic = (TextView) v.findViewById(R.id.tv_beauty_pic);
        tv_pic_info = (TextView) v.findViewById(R.id.tv_pic_info);

        tv_beauty_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beautyPic(picture.getPath());
            }
        });
        tv_pic_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initExif(picture.getPath());
            }
        });


    }

    private void beautyPic(String path){
        Log.d("wmy","--------------path:"+path);
        File file = new File(path);
        Bitmap bitmap = BitmapHelper.getBitmap(file);
        TuSdkResult result = new TuSdkResult();
        result.image = bitmap;
        openEditMultiple(result,null,null);
    }
    /** 开启照片美化组件 */
    private void openEditMultiple(TuSdkResult result, Error error, TuFragment lastFragment)
    {
        Log.d("wmy","openEditMultiple-------------");
        if (result == null || error != null) return;
        this.componentHelper = new TuSdkHelperComponent(mContext);
        // 组件委托
        TuSdkComponent.TuSdkComponentDelegate delegate = new TuSdkComponent.TuSdkComponentDelegate()
        {
            @Override
            public void onComponentFinished(TuSdkResult result, Error error, TuFragment lastFragment)
            {
                TLog.d("onEditMultipleComponentReaded: %s | %s", result, error);
                Log.d("wmy","onComponentFinished-------------");

                // 默认输出为 Bitmap  -> result.image
                // 如果保存到临时文件 (默认不保存, 当设置为true时,
                // TuSdkResult.imageFile, 处理完成后将自动清理原始图片)
                // option.setSaveToTemp(true);  ->  result.imageFile

                // 保存到系统相册 (默认不保存, 当设置为true时, TuSdkResult.sqlInfo, 处理完成后将自动清理原始图片)
                // option.setSaveToAlbum(true);  -> result.image
            }
        };

        // 组件选项配置
        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuEditMultipleComponent.html
        TuEditMultipleComponent component = null;

        if (lastFragment == null)
        {
            component = TuSdkGeeV1.editMultipleCommponent(this.componentHelper.activity(), delegate);
        }
        else
        {
            component = TuSdkGeeV1.editMultipleCommponent(lastFragment, delegate);
        }

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuEditMultipleComponentOption.html
        // component.componentOption()     //TuEditMultipleComponentOption

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/edit/TuEditMultipleOption.html
        // component.componentOption().editMultipleOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/edit/TuEditCuterOption.html
        // component.componentOption().editCuterOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditFilterOption.html
        // component.componentOption().editFilterOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditSkinOption.html
        // component.componentOption().editSkinOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/sticker/TuEditStickerOption.html
        // component.componentOption().editStickerOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditAdjustOption.html
        // component.componentOption().editAdjustOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditSharpnessOption.html
        // component.componentOption().editSharpnessOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditApertureOption.html
        // component.componentOption().editApertureOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditVignetteOption.html
        // component.componentOption().editVignetteOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/smudge/TuEditSmudgeOption.html
        // component.componentOption().editSmudgeOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditWipeAndFilterOption.html
        // component.componentOption().editWipeAndFilterOption()

        // 设置图片
        component.setImage(result.image)
                // 设置系统照片
                .setImageSqlInfo(result.imageSqlInfo)
                // 设置临时文件
                .setTempFilePath(result.imageFile)
                // 在组件执行完成后自动关闭组件
                .setAutoDismissWhenCompleted(false)
                // 开启组件
                .showComponent();
    }

    void initExif(String filePath) {

        try {
            ExifInterface exif = new ExifInterface(filePath);
             String tag_aperture = exif.getAttribute(ExifInterface.TAG_APERTURE);//光圈
             String dateTime = exif.getAttribute(ExifInterface.TAG_DATETIME);//日期时间
             String falsh = exif.getAttribute(ExifInterface.TAG_FLASH);//闪光灯
             String gpsLatitude= exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE) ;//纬度
             String gpsLatitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);//纬度参考
             String gpsLongitude = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);//经度
             String gpsLongitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);//经度参考
             String tag_iso = exif.getAttribute(ExifInterface.TAG_ISO);//ISO
             String make = exif.getAttribute(ExifInterface.TAG_MAKE);//图片制造商
             String tag_model = exif.getAttribute(ExifInterface.TAG_MODEL);//设备型号
             String orientation = exif.getAttribute(ExifInterface.TAG_ORIENTATION);//方向
             String whitebalance = exif.getAttribute(ExifInterface.TAG_WHITE_BALANCE);//白平衡

            Picture p = new Picture();
            p.setTag_aperture(tag_aperture);
            p.setDateTime(dateTime);
            p.setFalsh(falsh);
            p.setGpsLatitude(gpsLatitude);
            p.setGpsLatitudeRef(gpsLatitudeRef);
            p.setGpsLongitude(gpsLongitude);
            p.setGpsLongitudeRef(gpsLongitudeRef);
            p.setTag_iso(tag_iso);
            p.setMake(make);
            p.setSize("760*480");
            p.setTag_model(tag_model);
            p.setOrientation(orientation);
            p.setWhitebalance(whitebalance);
            p.setPath(filePath);

            Log.d("wmy",filePath);

            Intent intent = new Intent(mContext, InfoActivity.class);

            Bundle bundle = new Bundle();
            intent.putExtra("pic_info",p);
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showSample(Activity activity) {

    }
}

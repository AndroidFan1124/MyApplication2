package com.example.myapplication.presenter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

//import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.ImageActivity;
import com.example.myapplication.entity.GridItem;
import com.example.myapplication.util.PopupWindowUtil;
import com.example.myapplication.view.IImageView;

/**
 * Created by Administrator on 2016/6/1.
 */
public class ImagePresenter {
    Context mContext;
    IImageView view;
    String filePath = "";

    public ImagePresenter(IImageView view, ImageActivity mContext) {
        this.mContext = mContext;
        this.view = view;
    }

    public void showImage(GridItem item) {
        filePath = item.getPath();
        Log.d("wmy", "path:" + filePath);
//        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//        Log.d("wmy", "bitmap:" + bitmap);
//        view.getImageDetail().setImageBitmap(bitmap);
        Glide.with(mContext)
                .load(filePath)
                .into(view.getImageDetail());
    }

    public void animationDown(View view) {
        PopupWindowUtil util = new PopupWindowUtil();
        PopupWindow p = util.showPopupInfo(R.layout.view_popupwindow_up, mContext);
//        findViewById(R.id.parent), Gravity.CENTER
//                | Gravity.CENTER, 0, 0);
        p.showAtLocation(view, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}

package com.example.myapplication.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * Created by wangmengyan on 2016/6/1.
 */
public class PopupWindowUtil {

    TextView tv_pic_info;
    TextView tv_beauty_pic;

    public View showPopupInfo(int layoutId, Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, null);
        tv_pic_info = (TextView) view.findViewById(R.id.tv_pic_info);
        tv_beauty_pic = (TextView) view.findViewById(R.id.tv_beauty_pic);
        final PopupWindow popup = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popup.setFocusable(true);//设置可点击
        popup.setAnimationStyle(R.animator.anim);//设置动画效果
        //设置点击空白消失
        popup.setTouchable(true);
        popup.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popup.setBackgroundDrawable(dw);
        popup.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//        if(page.equals("imageActivity")){
//
//            tv_pic_info.setText("图片信息");
//            tv_beauty_pic.setText("美化图片");
//        }else if(page.equals("placeFragment")){
//            tv_pic_info.setText("从图库中选择");
//            tv_beauty_pic.setText("从时间列表中选择");
//        }
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = view.findViewById(R.id.rl_info).getTop();
                int y = (int)motionEvent.getY();
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(y<height)
                        popup.dismiss();
                }
                return true;
            }
        });
        return view;

    }
}

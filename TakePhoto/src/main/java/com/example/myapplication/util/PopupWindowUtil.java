package com.example.myapplication.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.myapplication.R;

/**
 * Created by wangmengyan on 2016/6/1.
 */
public class PopupWindowUtil {

    ImageView btn_info;

    public PopupWindow showPopupInfo(int layoutId, Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, null);
        btn_info = (ImageView) view.findViewById(R.id.iv_info);
//        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
//        int width = param.width;
//        int height = param.height;
        final PopupWindow popup = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popup.setFocusable(true);//设置可点击
        popup.setAnimationStyle(R.animator.anim);//设置动画效果
        //设置点击空白消失
        popup.setTouchable(true);
        popup.setOutsideTouchable(true);
        //popup.setBackgroundDrawable(android.R.color.transparent);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popup.setBackgroundDrawable(dw);

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

        return popup;

    }
}

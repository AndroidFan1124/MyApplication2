package com.example.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * Created by Administrator on 2016/5/30.
 */
public class TitleBarView extends RelativeLayout {
    RelativeLayout title_bar;
    Button btn_time;
    Button btn_place;
    Context mContext;
    private TextView tv_center;
    private LinearLayout common_constact;

    public TitleBarView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.common_title_bar,this);

        btn_place = (Button)findViewById(R.id.btn_place);
        btn_time = (Button)findViewById(R.id.btn_time);
        tv_center = (TextView) findViewById(R.id.title_txt);
        common_constact = (LinearLayout) findViewById(R.id.common_constact);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);

    }

    public void setCommonTitle(int LeftVisibility, int centerVisibility,
                               int center1Visibilter, int rightVisibility) {
        tv_center.setVisibility(centerVisibility);
        common_constact.setVisibility(center1Visibilter);

    }

    public Button getTitleLeft() {
        return btn_time;
    }

    public Button getTitleRight() {
        return btn_place;
    }


    public void setTitleLeft(int resId) {
        btn_time.setText(resId);

    }

    public void setTitleRight(int resId) {
        btn_place.setText(resId);
    }
}

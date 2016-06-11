package com.example.myapplication.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.R;
import com.example.myapplication.activity.FirstActivity;
import com.example.myapplication.activity.SecondActivity;
import com.example.myapplication.activity.ThirdActivity;
import com.example.myapplication.app.App;
import com.example.myapplication.util.ScreenUtil;

/**
 * Created by Administrator on 2016/5/26.
 */
public class FooterView extends RadioGroup implements View.OnClickListener {

    private RadioButton tab_first;
    private RadioButton tab_second;
    private RadioButton tab_third;
    public static final int PAGE_ONE = 1;
    public static final int PAGE_TWO = 2;
    public static final int PAGE_THIRD = 3;


    public FooterView(Context context) {
        super(context);
        initView(context);
        initLier();
    }


    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initLier();
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_footer, null);
        tab_first = (RadioButton) view.findViewById(R.id.rb_first);
        tab_second = (RadioButton) view.findViewById(R.id.rb_second);
        tab_third = (RadioButton) view.findViewById(R.id.rb_third);


        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tab_first.getLayoutParams();
        params.width = ScreenUtil.getScrrenWidth() / 3;
        tab_first.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) tab_second.getLayoutParams();
        params1.width = ScreenUtil.getScrrenWidth() / 3;
        tab_second.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) tab_third.getLayoutParams();
        params2.width = ScreenUtil.getScrrenWidth() / 3;
        tab_third.setLayoutParams(params2);

        addView(view);
    }

    private void initLier() {
        tab_first.setOnClickListener(this);
        tab_second.setOnClickListener(this);
        tab_third.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("wmy","view-->Id"+view.getId());
        switch (view.getId()) {
            case R.id.rb_first:
                setCurrentPage(PAGE_ONE);
                break;
            case R.id.rb_second:
                setCurrentPage(PAGE_TWO);
                break;
            case R.id.rb_third:
                setCurrentPage(PAGE_THIRD);
                break;
        }

    }

    private void setCurrentPage(int page) {
        Log.d("wmy","page"+page);
        Intent intent = new Intent();
        switch (page) {
            case PAGE_ONE:
                intent.setClass(App.getInstance(), FirstActivity.class);
                break;
            case PAGE_TWO:
                intent.setClass(App.getInstance(), SecondActivity.class);
                break;
            case PAGE_THIRD:
                intent.setClass(App.getInstance(), ThirdActivity.class);
                break;
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getInstance().startActivity(intent);
    }
}


package com.example.myapplication.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.myapplication.R;
import com.example.myapplication.app.BaseMainActivity;
import com.example.myapplication.fragment.OrderTimeFatherFragment;

/**
 * Created by Administrator on 2016/5/26.
 */
public class FirstActivity extends BaseMainActivity {
    private FrameLayout mFrameLayout;
    private OrderTimeFatherFragment mTimeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
//        initView();
//        initData();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mTimeFragment = new OrderTimeFatherFragment();
        transaction.add(R.id.id_content,mTimeFragment);
        transaction.commit();
    }

//    private void initView() {
//        mFrameLayout = (FrameLayout)findViewById(R.id.id_content);
//    }
}

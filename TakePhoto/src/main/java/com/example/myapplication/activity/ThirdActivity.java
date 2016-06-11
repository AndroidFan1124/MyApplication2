package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.app.BaseMainActivity;
import com.example.myapplication.widget.CircleImageView;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ThirdActivity extends BaseMainActivity implements View.OnClickListener{
    private RelativeLayout rl_user;
    private TextView tv_username;
    private CircleImageView civ_userIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initEvents();
        initData();

    }

    private void initView() {
        rl_user = (RelativeLayout)findViewById(R.id.rl_user);
        tv_username = (TextView)findViewById(R.id.tv_username);
        civ_userIcon = (CircleImageView)findViewById(R.id.civ_userIcon);
    }

    private void initEvents() {
        rl_user.setOnClickListener(this);

    }

    private void initData() {
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        Intent intent = null;
        switch (arg0.getId()){
            case R.id.rl_user:
                intent = new Intent(ThirdActivity.this,LoginActivity.class);
                break;
            default :break;

        }
        if(intent!=null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}

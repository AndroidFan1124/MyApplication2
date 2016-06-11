package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.app.BaseMainActivity;
import com.example.myapplication.presenter.SecondPresenter;
import com.example.myapplication.view.ISecondView;

/**
 * Created by Administrator on 2016/5/26.
 */
public class SecondActivity extends BaseMainActivity implements ISecondView, View.OnClickListener {

    private Button beau_carmea;
    private Button beau_pic;
    private SecondPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initEvents();
        initData();
    }

    private void initView() {
        beau_carmea = (Button) findViewById(R.id.btn_beau_carmea);
        beau_pic = (Button) findViewById(R.id.btn_beau_pic);
    }

    private void initEvents() {
        beau_carmea.setOnClickListener(this);
        beau_pic.setOnClickListener(this);
    }

    private void initData() {
        presenter = new SecondPresenter(this);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()) {
            case R.id.btn_beau_carmea:
                presenter.openCarmea(SecondActivity.this);
                break;
            case R.id.btn_beau_pic:
                presenter.beautyPic(SecondActivity.this);
                break;
            default:
                break;
        }
    }
}

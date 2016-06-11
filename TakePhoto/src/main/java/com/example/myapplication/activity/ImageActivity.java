package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapplication.R;
import com.example.myapplication.entity.GridItem;
import com.example.myapplication.presenter.ImagePresenter;
import com.example.myapplication.view.IImageView;

/**
 * Created by wangmengyan on 2016/6/1.
 */
public class ImageActivity extends Activity implements IImageView,View.OnClickListener{

    ImageView iv_detail,iv_info;
    ImagePresenter presenter;
    RelativeLayout rl_info;
    View view = null;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(this).inflate(R.layout.activity_image,null);
        mContext = this;
        setContentView(view);
        initView();
        initData();
        initEvents();

    }

    private void initEvents() {
        iv_detail.setOnClickListener(this);
    }

    private void initData() {
//        img_item
        presenter = new ImagePresenter(this,ImageActivity.this);
        Intent intent = getIntent();
        GridItem item = (GridItem) intent.getSerializableExtra("img_item");
        presenter.showImage(item);
    }

    private void initView() {
        iv_detail = (ImageView)findViewById(R.id.iv_detail_img);
        iv_info = (ImageView)findViewById(R.id.iv_info);
        rl_info = (RelativeLayout)findViewById(R.id.rl_info);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    public ImageView getImageDetail() {
        return iv_detail;
    }

    @Override
    public ImageView getImageInfo() {
        return iv_info;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.iv_detail_img:
                    presenter.animationDown(view);
                break;
            case R.id.iv_info:
                break;

        }
    }
}

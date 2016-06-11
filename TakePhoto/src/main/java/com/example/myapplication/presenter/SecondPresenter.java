package com.example.myapplication.presenter;

import android.app.Activity;

import com.example.myapplication.suite.CameraComponentSample;
import com.example.myapplication.suite.EditMultipleComponentSample;
import com.example.myapplication.view.ISecondView;

/**
 * Created by Administrator on 2016/6/6.
 */
public class SecondPresenter {
    ISecondView view;

    public SecondPresenter(ISecondView view){
        this.view =  view;

    }

    public void openCarmea(Activity activity) {
        new CameraComponentSample().showSample(activity);

    }

    public void beautyPic(Activity activity) {
        new EditMultipleComponentSample().showSample(activity);
    }
}

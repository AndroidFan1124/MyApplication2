package com.example.myapplication.presenter;

import android.content.Context;

import com.example.myapplication.view.IInfoView;

/**
 * Created by Administrator on 2016/6/12.
 */
public class InfoPresenter {
    IInfoView view;

    public InfoPresenter (IInfoView view, Context mContext){
        this.view = view;

    }
}

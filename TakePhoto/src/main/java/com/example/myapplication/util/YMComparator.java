package com.example.myapplication.util;

import com.example.myapplication.entity.Picture;

import java.util.Comparator;

/**
 * Created by wangmengyan on 2016/5/17.
 */
public class YMComparator implements Comparator<Picture> {

    @Override
    public int compare(Picture gridItem, Picture t1) {
        return gridItem.getTime().compareTo(t1.getTime());
    }
}

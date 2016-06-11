package com.example.myapplication.util;

import com.example.myapplication.entity.GridItem;

import java.util.Comparator;

/**
 * Created by wangmengyan on 2016/5/17.
 */
public class YMComparator implements Comparator<GridItem> {

    @Override
    public int compare(GridItem gridItem, GridItem t1) {
        return gridItem.getTime().compareTo(t1.getTime());
    }
}

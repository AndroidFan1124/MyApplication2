package com.example.myapplication.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/17.
 */
public class GridItem implements Serializable{
    private String path;
    private String time;
    private int section;

    public GridItem(String path, String time) {
        this.path = path;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

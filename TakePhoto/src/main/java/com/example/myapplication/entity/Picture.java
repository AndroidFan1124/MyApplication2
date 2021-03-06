package com.example.myapplication.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/31.
 */
public class Picture implements Serializable{

    private Integer id;//id
    private String path;//路径
    private String time;//时间

    private Place place;//地点信息
    private int section;//头部




    /**
     * 以下是EXIF信息
     */
    private String tag_aperture;//光圈
    private String dateTime ;//日期时间
    private String falsh ;//闪光灯
    private String gpsLatitude ;//纬度
    private String gpsLatitudeRef ;//纬度参考
    private String gpsLongitude ;//经度
    private String gpsLongitudeRef ;//经度参考
    private String tag_iso ;//ISO
    private String make ;//图片制造商
    private String tag_model ;//设备型号
    private String orientation ;//方向
    private String whitebalance ;//白平衡

    private  String size;//大小

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag_aperture() {
        return tag_aperture;
    }

    public void setTag_aperture(String tag_aperture) {
        this.tag_aperture = tag_aperture;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFalsh() {
        return falsh;
    }

    public void setFalsh(String falsh) {
        this.falsh = falsh;
    }


    public String getGpsLatitudeRef() {
        return gpsLatitudeRef;
    }

    public void setGpsLatitudeRef(String gpsLatitudeRef) {
        this.gpsLatitudeRef = gpsLatitudeRef;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(String gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public String getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getGpsLongitudeRef() {
        return gpsLongitudeRef;
    }

    public void setGpsLongitudeRef(String gpsLongitudeRef) {
        this.gpsLongitudeRef = gpsLongitudeRef;
    }

    public String getTag_iso() {
        return tag_iso;
    }

    public void setTag_iso(String tag_iso) {
        this.tag_iso = tag_iso;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getTag_model() {
        return tag_model;
    }

    public void setTag_model(String tag_model) {
        this.tag_model = tag_model;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getWhitebalance() {
        return whitebalance;
    }

    public void setWhitebalance(String whitebalance) {
        this.whitebalance = whitebalance;
    }
}

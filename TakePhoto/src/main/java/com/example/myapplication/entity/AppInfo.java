package com.example.myapplication.entity;

/**
 * Created by Administrator on 2016/6/14.
 */
public class AppInfo {
    private String appLabel;//应用程序标签
    private String appName ;//应用名称
    private String pkgName;//包名称
    private String directoryName;//对应的文件名

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
}

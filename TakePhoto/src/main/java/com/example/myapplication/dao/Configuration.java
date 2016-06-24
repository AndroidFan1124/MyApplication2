package com.example.myapplication.dao;

public class Configuration {

	public static final String DB_NAME="photo.db";
    public static int oldVersion;
    public static final String DB_PATH="db";
    public static final int DB_VERSION=1;
    public static final String PACKAGE_NAME="com.example.myapplication";
    public static String ROM_FILE=android.os.Environment.getDataDirectory().getAbsolutePath()+"/data/"+PACKAGE_NAME+"/";
    public static final String DB_DIR_PATH=ROM_FILE+"databases/";
    public static final String DB_FULL_PATH=DB_DIR_PATH+DB_NAME;
    public static final String TAG = "wmy";


	
}


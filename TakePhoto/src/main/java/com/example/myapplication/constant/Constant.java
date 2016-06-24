package com.example.myapplication.constant;

public class Constant {

	/**
	 * Server Info
	 *
	 */
	public static final String TAG = "wmy";
	public static final String Apikey = "";
	public final static String BASE_URL = "http://192.168.1.104:8080/";
	public final static String PROJECT_NAME="Photo";
	public final static String SERVER_NAME= PROJECT_NAME+"/dispatchRequest.action";
	public final static String ApiKey = "123456";
	public static final String NetworkUrl = BASE_URL+SERVER_NAME;
	public static final String SERVER_REAL_PATH = BASE_URL+PROJECT_NAME+"/";


	/**
	 * Code Info
	 */
	public static final int LOGIN_CODE = 300;
	public static final int LOGIN_ERROR_CODE = 300;


	public static final int REGISTER_COED = 301;
	public static final int REGISTER_ERROR_CODE = -301;



}
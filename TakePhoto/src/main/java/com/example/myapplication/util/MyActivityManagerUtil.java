package com.example.myapplication.util;

import android.app.Activity;

import java.util.Stack;

public class MyActivityManagerUtil {

	private static Stack<Activity> activityStack;

	private static MyActivityManagerUtil instance;

	private MyActivityManagerUtil() {
	}

	public static MyActivityManagerUtil getActivityManager() {
		if (instance == null)
			instance = new MyActivityManagerUtil();
		return instance;
	}
	//�Ƴ�һ��activity
	public void popActivity(Activity activity) {

		if (activity != null) {
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}
//	//�Ƴ�һ��activity
//	public void popOneActivity(Activity activity) {
//	    if (activityStack != null && activityStack.size() > 0) {
//	        if (activity != null) {
//	            activity.finish();
//	            activityStack.remove(activity);
//	            activity = null;
//	        }
//	    }
//	}
	public Activity currentActivity() {
		// TODO Auto-generated method stub
		Activity activity = activityStack.lastElement();
		System.out.println("lastElement----->"+activity);
		return activity;
	}
	//��һ��activityѹ��ջ��
	public void pushActivity(Activity activity) {

		if (activityStack == null)
			activityStack = new Stack<Activity>();
		activityStack.add(activity);
	}

//	public void popAllActivityExceptOne(Class cls) {
//
//		while (true) {
//			Activity activity = currentActivity();
//			if (activity == null)
//				break;
//			if (activity.getClass().equals(cls))
//				break;
//			popActivity(activity);
//
//		}
//
//	}

	public  void finishAllActivity() {
		// TODO Auto-generated method stub
		 if (activityStack != null) {
		        while (activityStack.size() > 0) {
		            Activity activity = currentActivity();
		            if (activity == null) break;
		            popActivity(activity);
		        }
		    }
	}
	 
}

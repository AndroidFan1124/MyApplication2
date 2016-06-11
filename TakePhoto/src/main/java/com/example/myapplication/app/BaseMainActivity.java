package com.example.myapplication.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.myapplication.R;
import com.example.myapplication.util.MyActivityManagerUtil;
import com.example.myapplication.util.ScreenUtil;
import com.example.myapplication.view.IBaseMainView;


public class BaseMainActivity extends BaseActivity implements IBaseMainView,
		OnClickListener {

	private FrameLayout content;
	private FrameLayout frame_all;
	private MyActivityManagerUtil manager;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_basemain);
		initView();
		initData();
		initLier();
	}


	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		Log.d("wangmengyan", "---->" + "BaseMainActivity" + "---->"
				+ "setContentView" + layoutResID);
		LayoutInflater.from(this).inflate(layoutResID, content, true);
	}

	private void initView() {
		// TODO Auto-generated method stub
		content = (FrameLayout) findViewById(R.id.content);
		frame_all = (FrameLayout) findViewById(R.id.frame_all);

	}

	private void initData() {
		// TODO Auto-generated method stub
		manager = manager.getActivityManager();
		manager.pushActivity(this);
		initSlide();

	}



	private void initSlide() {
		// TODO Auto-generated method stub
		ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(
				ScreenUtil.getScrrenWidth(), ScreenUtil.getScreenHeight());
		frame_all.setLayoutParams(params);
		frame_all.getLayoutParams();
	}

	private void initLier() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	public void exitDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("确定要退出吗");
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						manager.finishAllActivity();
						android.os.Process.killProcess(android.os.Process
								.myPid());

					}
				});
		builder.setNegativeButton("取消",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
		builder.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
			exitDialog();
		return super.onKeyDown(keyCode, event);
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	@Override
	public void onReceive(Object obj) {
		// TODO Auto-generated method stub
		Log.d("wangmengyan", "basemain----onreceive>"+obj.toString());
	}
	@Override
	public void onGetReceive(Object obj) {
		// TODO Auto-generated method stub
		super.onGetReceive(obj);
		Log.d("wangmengyan", "basemain----getreceive>"+obj.toString());
	}

}

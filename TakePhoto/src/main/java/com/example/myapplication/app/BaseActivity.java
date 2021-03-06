package com.example.myapplication.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.android.volley.VolleyError;
import com.example.myapplication.networker.Network;
import com.example.myapplication.networker.NetworkWatched;
import com.example.myapplication.networker.NetworkWatcher;

public class BaseActivity extends Activity  implements NetworkWatcher {


	private Network nw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initWindow();
		initData();
	}

	public NetworkWatched getNetwork() {
		return nw;
	}

	private void initWindow() {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}



	private void initData() {
		// TODO Auto-generated method stub
//		PackageUtil.getAppInfo();
		//检测SD卡是否存在
//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {
//			File path = Environment.getExternalStorageDirectory();
//		}else{
//			Toast.makeText(this, "没有SD卡", Toast.LENGTH_LONG).show();
//			finish();
//		}
//		Map<String,List<File>> map = PackageUtil.getAllAppPic();
//		if(map!=null) {
//			Set<String> set = map.keySet();
//			for (String s : set) {
//				List<File> listFile = map.get(s);
//				for (File f : listFile) {
//					Log.d("wmy", f.getPath() + "------>" + f.getName());
//				}
//			}
//		}
		nw = new Network();
		nw.addNetworkWatched(this);
	}



	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		nw.removeNetworkWatched(this);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

	@Override
	public void onReceive(Object obj) {

	}

	@Override
	public void onReceiveError(VolleyError error) {

	}

	@Override
	public void onPostReceive(Object obj) {

	}

	@Override
	public void onGetReceive(Object obj) {

	}

	@Override
	public void onErrorReceive(Object obj) {

	}


}

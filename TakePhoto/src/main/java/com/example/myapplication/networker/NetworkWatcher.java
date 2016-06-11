package com.example.myapplication.networker;

import com.android.volley.VolleyError;


public interface NetworkWatcher {


	public abstract void onReceive(Object obj);

	public abstract void onReceiveError(VolleyError error);

	public abstract void onPostReceive(Object obj);
	
	public abstract void onGetReceive(Object obj);
	
	public abstract void onErrorReceive(Object obj);
	
}

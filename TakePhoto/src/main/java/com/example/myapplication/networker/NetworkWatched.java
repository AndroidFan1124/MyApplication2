package com.example.myapplication.networker;

import com.android.volley.VolleyError;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * ����
 * @author wangmengyan
 *
 */
public interface NetworkWatched {


	public void addNetworkWatched(NetworkWatcher nw);
	

	public void removeNetworkWatched(NetworkWatcher nw);
	

	public void notifyNetworkWatched(Object obj);
	
	public void notifyNetworkWatchedError(VolleyError error);

	public void requestDispatch(String input);
	

	public void doGet(String input);
	
	public void doPost(String url, List<NameValuePair> listparam,
					   boolean issavecookie);
	
	public void onGetReceive(Object obj);
	
	public void onPostReceive(Object obj);
	
	public void onErrorReceive(Object obj);
}

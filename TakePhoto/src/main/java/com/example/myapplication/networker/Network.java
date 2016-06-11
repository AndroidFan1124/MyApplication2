package com.example.myapplication.networker;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.app.App;
import com.example.myapplication.constant.Constant;
import com.example.myapplication.dao.Configuration;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network implements NetworkWatched {

	private final int GET_RESULT = 2;
	private final int GET_POST = 3;
	private Network singleton;
	private NetworkWatcher netWatcher;
	private RequestQueue mQueue;
	String TAG = Configuration.TAG;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_RESULT:
				if (netWatcher != null){
					Log.d("wangmengyan","network--->"+msg.obj);
					netWatcher.onGetReceive(msg.obj);
				}
				break;
			case GET_POST:
				if (netWatcher != null)
					netWatcher.onPostReceive(msg.obj);
				break;
			default:
				break;
			}
		}
	};
	private String mIMEIString;
	private String mNameString;

	public Network() {
		mQueue = Volley.newRequestQueue(App.getInstance());
	}
	@Override
	public void addNetworkWatched(NetworkWatcher nw) {
		// TODO Auto-generated method stub
		netWatcher = nw;
	}

	@Override
	public void removeNetworkWatched(NetworkWatcher nw) {
		// TODO Auto-generated method stub
		netWatcher = null;
	}

	@Override
	public void notifyNetworkWatched(Object obj) {
		// TODO Auto-generated method stub
		if (netWatcher != null)
			netWatcher.onReceive(obj);
	}

	@Override
	public void notifyNetworkWatchedError(VolleyError error) {
		// TODO Auto-generated method stub
		if (netWatcher != null)
			netWatcher.onReceiveError(error);
	}

	@Override
	public void requestDispatch(String input) {
		// TODO Auto-generated method stub
		Log.d("wmy","requestDispatch()");
		getInstanceNetwork();
		initJsonPost(input);
	}

	private Network getInstanceNetwork() {
		// TODO Auto-generated method stub
		singleton = new Network();
		TelephonyManager manager = (TelephonyManager) App.getInstance()
				.getSystemService(Context.TELEPHONY_SERVICE);
		mIMEIString = manager.getDeviceId();
		mNameString = Build.MODEL;
		return null;

	}

	private void initJsonPost(String input) {
		// TODO Auto-generated method stub
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(input);
		JsonObject jo = je.getAsJsonObject();
//		jo.addProperty("MIME", mIMEIString);
//		jo.addProperty("NAME", mNameString);
//		jo.addProperty("ApiKey", Constant.Apikey);
		JSONObject jjo = null;
		try {
			jjo = new JSONObject(jo.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		requestJson(jjo);
	}

	private void requestJson(final JSONObject jjo) {
		// TODO Auto-generated method stub
		System.out.println(TAG + "--->System--->" + jjo.toString());
		Log.d("wmy","Constant.NetworkUrl:"+	Constant.NetworkUrl);
		mQueue = Volley.newRequestQueue(App.getInstance());
		StringRequest request = new StringRequest(Request.Method.POST,
				Constant.NetworkUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						notifyNetworkWatched(response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						notifyNetworkWatchedError(error);
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("params", jjo.toString());
				Log.d("wmy","param:" +jjo.toString());
				return map;
			}
		};
		request.setRetryPolicy(new DefaultRetryPolicy(1000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mQueue.add(request);
	}

	@Override
	public void doGet(final String input) {
		// TODO Auto-generated method stub
		Log.d(TAG, input);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					HttpGet httpGet = new HttpGet(input);
					httpGet.setHeader("apikey", Constant.Apikey);
					HttpClient client = new DefaultHttpClient();
					HttpResponse response = client.execute(httpGet);
					String result = EntityUtils.toString(response.getEntity());
					onGetReceive(result);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void doPost(final String url, final List<NameValuePair> listparam,
			final boolean isLogin) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpPost post = new HttpPost(url);
				try {
					post.setEntity(new UrlEncodedFormEntity(listparam,
							HTTP.UTF_8));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HttpClient client = new DefaultHttpClient();
				/**
				 * cookieû�з�====================>>>>
				 */

				try {
					if (!isLogin) {
						// ============================>>>>>
					}
					HttpResponse httpResponse = client.execute(post);
					String result = EntityUtils.toString(httpResponse
							.getEntity());
					if (isLogin) {
						// ============================>>>>>
					}
					onPostReceive(result);

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	@Override
	public void onGetReceive(Object obj) {
		// TODO Auto-generated method stub
		Message msg = Message.obtain();
		msg.obj = obj;
		msg.what = GET_RESULT;
		handler.sendMessage(msg);

	}

	@Override
	public void onPostReceive(Object obj) {
		// TODO Auto-generated method stub
		Message msg = Message.obtain();
		msg.obj = obj;
		msg.what = GET_POST;
		handler.sendMessage(msg);

	}

	@Override
	public void onErrorReceive(Object obj) {
		// TODO Auto-generated method stub
		if (netWatcher != null)
			netWatcher.onErrorReceive(obj);
	}

}

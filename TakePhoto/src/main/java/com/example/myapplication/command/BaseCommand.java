package com.example.myapplication.command;

import com.example.myapplication.networker.NetworkWatched;

import org.apache.http.NameValuePair;

import java.util.List;


public class BaseCommand implements Command {

	@Override
	public boolean execute(Object obj, NetworkWatched nw) {
		// TODO Auto-generated method stub
		return false;
	}

	public void doGet(NetworkWatched nw, String input) {
		nw.doGet(input);
	}

	public void doPost(NetworkWatched nw, String url,
			List<NameValuePair> listparam, boolean issavecookie) {
		nw.doPost(url, listparam, issavecookie);
	}
}

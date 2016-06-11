package com.example.myapplication.command;


import com.example.myapplication.networker.NetworkWatched;

public interface Command {

	public abstract boolean execute(Object obj, NetworkWatched nw);
}

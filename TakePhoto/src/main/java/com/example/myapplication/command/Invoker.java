package com.example.myapplication.command;


import com.example.myapplication.networker.NetworkWatched;

public class Invoker {

	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public boolean execute(Object obj, NetworkWatched nw) {
		return command.execute(obj, nw);
	}
}

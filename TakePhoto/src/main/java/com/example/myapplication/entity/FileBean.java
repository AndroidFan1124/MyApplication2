package com.example.myapplication.entity;


import com.example.myapplication.treeutil.TreeNodeId;
import com.example.myapplication.treeutil.TreeNodeLabel;
import com.example.myapplication.treeutil.TreeNodePid;

public class FileBean {

	@TreeNodeId
	private int _id;
	@TreeNodePid
	private int pid;
	@TreeNodeLabel
	private String name;
	
	private String desc;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public FileBean(int _id, int pid, String name) {
		super();
		this._id = _id;
		this.pid = pid;
		this.name = name;
	}
	
	
	
	
}

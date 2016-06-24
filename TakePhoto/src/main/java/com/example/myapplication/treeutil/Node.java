package com.example.myapplication.treeutil;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int _id;
	private int pid;// 根节点
	private String name;

	private int level;// 树的层级

	// 是否展开
	private boolean isExpand = false;

	private int icon;

	public Node(int _id, int pid, String name) {
		super();
		this._id = _id;
		this.pid = pid;
		this.name = name;
	}

	// 返回父节点
	private Node parent;

	// 返回父节点的所有字结点
	private List<Node> children = new ArrayList<Node>();

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

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	/**
	 * 是否是根节点
	 *
	 * @return
	 */
	public boolean isRoot() {
		return parent == null;
	}

	/**
	 * 判断父节点是展开状态还是合并状态
	 *
	 * @return
	 */
	public boolean isParentExpand() {
		if (parent == null)
			return false;
		return parent.isExpand();
	}

	/**
	 * 是否是叶子节点 叶子节点没有孩子了
	 */
	public boolean isLeaf() {
		return children.size() == 0;
	}

	/**
	 * 得到结点级别
	 *
	 * @return
	 */
	public int getLevel() {
		return parent == null ? 0 : parent.getLevel() + 1;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setIcon() {
	}

	public boolean isExpand() {
		return isExpand;
	}

	/**
	 * 设置伸缩状态 并且是否改变子节点的伸缩状态
	 *
	 * @param isExpand
	 */
	public void setExpand(boolean isExpand) {
		this.isExpand = isExpand;
		if (!isExpand) {
			for (Node node : children) {
				node.setExpand(false);
			}
		}

	}

	//是否是根节点
	public static boolean isParent(Node node){
		return node.getPid()==0;
	}
	//是否有孩子
}

package com.example.myapplication.treeutil;


import com.example.myapplication.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TreeHelper {

	/**
	 *
	 * @param list
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	// 将数据转换成Node
	public static <T> List<Node> convertData2Node(List<T> list)
			throws IllegalAccessException, IllegalArgumentException {
		List<Node> nodes = new ArrayList<Node>();
		Node node = null;
		for (T t : list) {
			int id = -1;
			int pid = -1;
			String label = null;

			// node = new Node();
			Class cls = t.getClass();
			Field[] fields = cls.getDeclaredFields();
			for (Field f : fields) {
				if (f.getAnnotation(TreeNodeId.class) != null) {
					f.setAccessible(true);// 设置访问权限
					id = f.getInt(t);

				}

				if (f.getAnnotation(TreeNodePid.class) != null) {
					f.setAccessible(true);// 设置访问权限
					pid = f.getInt(t);

				}

				if (f.getAnnotation(TreeNodeLabel.class) != null) {
					f.setAccessible(true);// 设置访问权限
					label = f.get(t).toString();

				}

			}
			node = new Node(id, pid, label);
			nodes.add(node);
		}

		/**
		 * 设置节点间的关系
		 */
		// 设置关联属性
		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			for (int j = i + 1; j < nodes.size(); j++) {
				Node m = nodes.get(j);
				// m的父节点是n
				if (m.getPid() == n.get_id()) {
					n.getChildren().add(m);
					m.setParent(n);
				} else if (n.getPid() == m.get_id()) {
					m.getChildren().add(n);
					n.setParent(m);

				}
			}
		}

		for (Node n : nodes) {
			setNodeIcon(n);
		}
		return nodes;

	}

	/**
	 * 为Node设置图标
	 *
	 * @param n
	 */
	private static void setNodeIcon(Node n) {
		// TODO Auto-generated method stub
		// 是展开状态
		if (n.getChildren().size() > 0 && n.isExpand()) {
			n.setIcon(R.mipmap.tree_ex);
		}// 不是展开状态
		else if (n.getChildren().size() > 0 && !n.isExpand()) {
			n.setIcon(R.mipmap.tree_ec);
		} else {
			n.setIcon(-1);
		}
	}

	// public static <T> List<Node> getSortedNodes(List<T> datas,
	// int defaultExpandLevel) throws IllegalArgumentException,
	// IllegalAccessException
	// {
	// List<Node> result = new ArrayList<Node>();
	// List<Node> nodes = convertDatas2Nodes(datas);
	// // 获得树的根结点
	// List<Node> rootNodes = getRootNodes(nodes);
	//
	// for (Node node : rootNodes)
	// {
	// addNode(result, node, defaultExpandLevel, 1);
	// }
	//
	// Log.e("TAG", result.size() + "");
	// return result;
	// }

	public static <T> List<Node> getSortedNodes(List<T> datas,
												int defaultExapndLevel) throws IllegalAccessException,
			IllegalArgumentException {
		List<Node> result = new ArrayList<Node>();
		List<Node> nodes = convertData2Node(datas);

		// 获得数的根节点
		List<Node> rootnodes = getRootNodes(nodes);

		for (Node node : rootnodes) {
			addNode(result, node, defaultExapndLevel, 1);
		}

		return result;

	}

	/**
	 * 把一个结点的所有孩子结点都放入Result
	 *
	 * @param result
	 * @param node
	 * @param defaultExapndLevel
	 * @param currentLevel
	 */
	private static void addNode(List<Node> result, Node node,
								int defaultExapndLevel, int currentLevel) {
		// TODO Auto-generated method stub
		result.add(node);// 添加根目录
		if (defaultExapndLevel >= currentLevel) {
			node.setExpand(true);
		}
		if (node.isLeaf())// 是叶子节点
			return;
		for (int i = 0; i < node.getChildren().size(); i++) {
			addNode(result, node.getChildren().get(i), defaultExapndLevel,
					currentLevel + 1);
		}
	}

	/**
	 * 从所有节点中过滤出所有很节点
	 *
	 * @param nodes
	 * @return
	 */
	private static List<Node> getRootNodes(List<Node> nodes) {
		// TODO Auto-generated method stub
		List<Node> root = new ArrayList<Node>();
		for (Node node : nodes) {
			if (node.isRoot())
				root.add(node);
		}
		return root;
	}

	/**
	 * 过滤出可见的结点
	 *
	 * @param nodes
	 * @return
	 */
	public static List<Node> filterVisibleNodes(List<Node> nodes) {

		List<Node> list = new ArrayList<Node>();
		for (Node node : nodes) {
			if (node.isRoot() || node.isParentExpand()) {
				setNodeIcon(node);
				list.add(node);
			}
		}
		return list;

	}
}

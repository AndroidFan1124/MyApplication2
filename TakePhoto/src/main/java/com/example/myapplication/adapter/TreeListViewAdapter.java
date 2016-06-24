package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.myapplication.treeutil.Node;
import com.example.myapplication.treeutil.TreeHelper;

import java.util.List;

public abstract class TreeListViewAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<Node> mVisibleNodes;
	protected List<Node> mAllNodes;

	protected LayoutInflater mInflater;
	private ListView mTree;

	// private int mDefaultLevel;

	public interface  OnTreenodeClickListener{
		void onClick(Node node,int position);
	}

	private OnTreenodeClickListener mListener;

	/**
	 * 设置Node的点击回调
	 */
	public void setOnTreeNodeClickListener(OnTreenodeClickListener mListener){
		this.mListener = mListener;
	}

	/**
	 *
	 * @param context
	 * @param datas
	 * @param defaultExapndLevel
	 *            用户控制默认显示几级 默认一级
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public TreeListViewAdapter(ListView tree, Context context, List<T> datas,
							   int defaultExapndLevel) throws IllegalAccessException,
			IllegalArgumentException {
		mContext = context;
//		Log.d("wmy","0aaa--->"+datas);
		mAllNodes = TreeHelper.getSortedNodes(datas, defaultExapndLevel);
//		Log.d("wmy","0aaa--->"+mAllNodes);
		mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
		// mDefaultLevel = defaultLevel;
		mTree = tree;
		mInflater = LayoutInflater.from(mContext);;
		mTree.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
									int position, long arg3) {
				// TODO Auto-generated method stub
				expanderOrCollapse(position);
				if(mListener != null){
					mListener.onClick(mVisibleNodes.get(position), position);
				}
			}

		});

	}

	private void expanderOrCollapse(int position) {
		// TODO Auto-generated method stub

		Node node =  mVisibleNodes.get(position);
		if(node !=null){
			if(node.isLeaf()){
				//叶子节点
			}
			node.setExpand(!node.isExpand());
			mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mVisibleNodes.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mVisibleNodes.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		Node node = mVisibleNodes.get(position);
		convertView = getConvertView(node, position, convertView, viewGroup);
		//设置内边距
		convertView.setPadding(node.getLevel()*30, 3,3,3);


		return convertView;
	}

	public abstract View getConvertView(Node node,int position ,View convertView ,ViewGroup viewGroup);
}

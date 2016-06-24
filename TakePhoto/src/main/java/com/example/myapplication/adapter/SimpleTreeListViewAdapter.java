package com.example.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.treeutil.Fileutil;
import com.example.myapplication.treeutil.Node;
import com.example.myapplication.treeutil.TreeHelper;
import com.example.myapplication.util.ToastUtil;

import java.util.List;


public class SimpleTreeListViewAdapter<T> extends TreeListViewAdapter<T> {

    public SimpleTreeListViewAdapter(ListView tree, Context context,
                                     List<T> datas, int defaultExapndLevel)
            throws IllegalAccessException, IllegalArgumentException {
        super(tree, context, datas, defaultExapndLevel);
        Log.d("wmy", "0Dtaas--->" + datas);

        // TODO Auto-generated constructor stub
    }

    @Override
    public View getConvertView(Node node, int position, View convertView,
                               ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.from(mContext).inflate(R.layout.tree_list_item,
                    viewGroup, false);
            holder = new ViewHolder();
            holder.item_img = (ImageView) convertView
                    .findViewById(R.id.id_item_icon);
            holder.item_text = (TextView) convertView
                    .findViewById(R.id.id_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (node.getIcon() == -1) {
            holder.item_img.setVisibility(View.INVISIBLE);
        } else {
            holder.item_img.setVisibility(View.VISIBLE);
            holder.item_img.setImageResource(node.getIcon());
        }
        holder.item_text.setText(node.getName());
        return convertView;
    }

    class ViewHolder {
        TextView item_text;
        ImageView item_img;

    }

    /**
     * 动态插入结点
     *
     * @param position
     * @param string
     */
    public void addExtraNode(int position, String string) {
        String parentName = "";
        // if (position != 0) {
        String aa = "";
        Node node = mVisibleNodes.get(position);// 得到父节点
        String path = getPath(node, aa);
        Log.d("wmy", "getPath--->" + path);
        parentName = path + string;
        boolean flag = Fileutil.newFile(parentName);
        if (flag) {
            int indexOf = mAllNodes.indexOf(node);
            // Node
            Node extraNode = new Node(-1, node.get_id(), string);
            extraNode.setParent(node);
            node.getChildren().add(extraNode);
            mAllNodes.add(indexOf + 1, extraNode);
            mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
        } else {
            ToastUtil.showShort("文件夹已存在");
        }

        notifyDataSetChanged();

    }

    public void createRoot(String string) {
        String parentName = string;
        boolean flag = Fileutil.newFile(parentName);
        if (flag) {
            Node extraNode = new Node(-1, 0, string);
            mAllNodes.add(extraNode);
            mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
        } else {
            ToastUtil.showShort("文件夹已存在");
        }
        notifyDataSetChanged();
    }

    private String bb = null;

    public String getPath(Node node, String aa) {
        if (node.isParent(node)) {
            aa = node.getName() + "/" + aa;
            bb = aa;
        } else {
            aa = node.getName() + "/" + aa;
            Node n = node.getParent();
            String temp = n.getName() + "/";
            getPath(n, aa);
        }
        return bb;
    }

//    /**
//     *
//     * @param position 点击父节点id
//     * @param path   图片存储路径
//     * @param name   图片文件名
//     * @param bitmap  图片
//     */
//    public void addPicExtraNode(int position, String path,String name, Bitmap bitmap) {
//
//        Node node = mVisibleNodes.get(position);// 得到父节点
//        int indexOf = mAllNodes.indexOf(node);
//        // Node
//        Node extraNode = new Node(-1, node.get_id(), name);
//        extraNode.setParent(node);
//        extraNode.setIcon();
//        node.getChildren().add(extraNode);
//        mAllNodes.add(indexOf + 1, extraNode);
//        mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
//        notifyDataSetChanged();
//
//    }
}

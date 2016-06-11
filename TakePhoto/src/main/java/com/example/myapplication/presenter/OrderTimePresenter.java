package com.example.myapplication.presenter;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myapplication.StickyGridAdapter;
import com.example.myapplication.activity.ImageActivity;
import com.example.myapplication.entity.GridItem;
import com.example.myapplication.util.ImageScanner;
import com.example.myapplication.util.YMComparator;
import com.example.myapplication.view.IOrderTimeView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by wangmengyan on 2016/5/31.
 */
public class OrderTimePresenter {

    IOrderTimeView view;
    //加载数据到该集合，封装到GridItem
    private Map<String, Integer> sectionMap = new HashMap<String, Integer>();
    private HashMap<Integer, Boolean> mSelectMap = new HashMap<Integer, Boolean>();
    private int width;
    private int checkNum;// 记录选中的条目数量ctionMap

    private static int section = 1;
    private StickyGridAdapter mAdapter;


    //    // 全选模式
    private boolean isSeletedAll = true;
    private boolean isSekected = false;

    private ImageScanner mScanner;

    private Context mContext;

    int count;
    // 正在删除dialogh
    private ProgressDialog dialog = null;
    public ProgressDialog mProgressDialog;

    private List<GridItem> mGirdList = new ArrayList<GridItem>();

    private GridView mGridview;
    private TextView seleted_all;
    private TextView show_num;

    public OrderTimePresenter(IOrderTimeView view){
        this.view = view;

    }
    private String paserTimeToYM(long times) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date(times * 1000L));

    }


    //初始化数据
    public void initData(){
        mGridview = view.getGridView();
        seleted_all = view.getSelectAllBtn();
        show_num = view.getShowNumTV();
        mContext = view.getContext_();
        mScanner = new ImageScanner(mContext);
        mScanner.scanImages(new ImageScanner.ScanCompleteCallBack() {
            {
                mProgressDialog = ProgressDialog.show(mContext, null, "正在加载...");
            }
            @Override
            public void scanComplete(Cursor cursor) {
                mProgressDialog.dismiss();
                Log.d("wmy", "cursor:" + cursor.moveToNext());
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    long times = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
                    GridItem item = new GridItem(path, paserTimeToYM(times));
                    mGirdList.add(item);
                }
                cursor.close();
                Collections.sort(mGirdList, new YMComparator());

                for (ListIterator<GridItem> it = mGirdList.listIterator(); it.hasNext(); ) {
                    GridItem mGridItem = it.next();
                    String ym = mGridItem.getTime();
                    if (!sectionMap.containsKey(ym)) {
                        mGridItem.setSection(section);
                        sectionMap.put(ym, section);
                        section++;
                    } else {
                        mGridItem.setSection(sectionMap.get(ym));
                    }
                }
                mAdapter = new StickyGridAdapter(mContext, mGirdList, mGridview, mSelectMap);
                mGridview.setAdapter(mAdapter);
            }
        });
    }
    //退出多选
    public void exitSeletedLoc(){

        isSekected = false;
        isSeletedAll = true;
        mAdapter.updateData(false);
        if (mSelectMap.size() == 0) {
            checkNum = 0;
        } else {
            mSelectMap.clear();
            checkNum = 0;
            dataChanged();
        }

    }

    //单击Item选择
    public void ItemClick(AdapterView<?> adapterView, View view, int position, long l){
        if (isSekected) {
            //在全选模式下的条目点击事件（选择or不选）
            StickyGridAdapter.ViewHolder holder = (StickyGridAdapter.ViewHolder) view.getTag();
            holder.checkBox.toggle();
            //将checkbox的状态记录下来
            StickyGridAdapter.getIsSelected().put(position, holder.checkBox.isChecked());
            //调整选定的条目
            if (holder.checkBox.isChecked() == true) {
                checkNum++;
                mGridview.setItemChecked(position, true);
                mSelectMap.put(position, true);
                if (checkNum == mGirdList.size()) {
                    isSeletedAll = false;
                    seleted_all.setText("全不选");
                }
            } else {
                checkNum--;
                mGridview.setItemChecked(position, false);
                mSelectMap.put(position, false);
                if (checkNum < mGirdList.size()) {
                    isSeletedAll = true;
                    seleted_all.setText("全选 ");
                }
            }
            dataChanged();
        } else {
            // 普通模式 大图预览
            Intent intent = new Intent(mContext, ImageActivity.class);
            intent.putExtra("img_item",mGirdList.get(position));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);

        }
    }


    //长按点击选择
    public void ItemLongClick(AdapterView<?> adapterView, View view, int i, long l){

        //长按
        isSekected = true;
//        selected_layout.setAnimation();

//        getActionBar().hide();
        mAdapter.updateData(true);
    }

    //全选与全不选
    public void seletedAllLoc(){
//全选 全不选
        if (isSeletedAll) {
            seleted_all.setText("全不选");
            for (int i = 0; i < mGirdList.size(); i++) {
                StickyGridAdapter.getIsSelected().put(i, true);
                mGridview.setItemChecked(i, true);
                mSelectMap.put(i, true);
            }
            // 选中的数量
            isSeletedAll = false;
            checkNum = mGirdList.size();
            dataChanged();
        } else {
            seleted_all.setText("全选");
            for (int i = 0; i < mGirdList.size(); i++) {
                if (StickyGridAdapter.getIsSelected().get(i)) {
                    StickyGridAdapter.getIsSelected().put(i, false);
                    mGridview.setItemChecked(i, false);
                    mSelectMap.put(i, false);
                    checkNum--;
                    mSelectMap.clear();
                }
            }
            isSeletedAll = true;
            // 改变适配器
            dataChanged();
        }
    }

    //选择删除
    public void delete(){
        // 删除

        final List<GridItem> list = new ArrayList<GridItem>();

        for (int i = 0; i < mGirdList.size(); i++) {
            if (mSelectMap.containsKey(i) == true) {
                list.add(mGirdList.get(i));
            }
        }
        if (list.size() <= 0) {
            return;
        }

        new removeTask().execute(list);

        isSekected = false;
        mAdapter.updateData(false);
        mSelectMap.clear();
        checkNum = 0;
        dataChanged();
    }


    private void dataChanged() {
        mAdapter.notifyDataSetChanged();
        show_num.setText("已选中" + checkNum + "项");
    }

    class removeTask extends AsyncTask<List<GridItem>, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(mContext);
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("正在删除，请稍后...");
            dialog.show();

        }

        @Override
        protected Integer doInBackground(List<GridItem>... params) {
            for (GridItem f : params[0]) {
                String path = f.getPath();
                mGirdList.remove(f);
                if (!TextUtils.isEmpty(path)) {
                    Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    ContentResolver mContentResolver = mContext
                            .getContentResolver();
                    String where = MediaStore.Images.Media.DATA + "='" + path
                            + "'";
                    // 删除图片
                    mContentResolver.delete(uri, where, null);

                    // 发送广播
                    Intent intent = new Intent(
                            Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    File file = new File(path);
                    Uri uri2 = Uri.fromFile(file);
                    intent.setData(uri2);
                    mContext.sendBroadcast(intent);
                }
            }
            count = mGirdList.size();
            return count;
        }

        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            dataChanged();
            dialog.cancel();

        }
    }
}

package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.presenter.OrderTimePresenter;
import com.example.myapplication.view.IOrderTimeView;

/**
 * Created by wangmengyan on 2016/5/30.
 */
public class OrderTimeFragment extends Fragment implements
        View.OnClickListener, AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener, IOrderTimeView {
    private Context mContext;
    private GridView mGridView;
    private FrameLayout selected_layout;
    //    private ImageButton album_back;
    private Button edit_seleted;
    private TextView seleted_all;
    private ImageButton delete_loc;
    private TextView show_num;
    //presenter
    OrderTimePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("wmy", "time");
        mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_time, container, false);
        initView(view);
        initEvents();
        initData();
        return view;
    }

    private void initView(View view) {
        mGridView = (GridView) view.findViewById(R.id.asset_grid);
        selected_layout = (FrameLayout) view.findViewById(R.id.seleted_layout);
        edit_seleted = (Button) view.findViewById(R.id.exit_seleted_loc);
        seleted_all = (TextView) view.findViewById(R.id.seleted_all_loc);
        delete_loc = (ImageButton) view.findViewById(R.id.delete_loc);
        show_num = (TextView) view.findViewById(R.id.show_num);
    }

    public void initEvents() {
        mGridView.setOnItemLongClickListener(this);
        mGridView.setOnItemClickListener(this);
        edit_seleted.setOnClickListener(this);
        seleted_all.setOnClickListener(this);
        delete_loc.setOnClickListener(this);
    }


    private void initData() {
        presenter = new OrderTimePresenter(this);
        presenter.initData();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //退出全选模式
            case R.id.exit_seleted_loc:
//                getActionBar().show();
                presenter.exitSeletedLoc();
                  break;
            case R.id.seleted_all_loc:
                presenter.seletedAllLoc();
                break;
            case R.id.delete_loc:
                selected_layout.setVisibility(View.GONE);
                presenter.delete();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //条目点击
        presenter.ItemClick(adapterView,view,position,l);
    }

    int count;


    @SuppressLint("NewApi")
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        selected_layout.setVisibility(View.VISIBLE);
        presenter.ItemLongClick(adapterView,view,i,l);
        return true;
    }


    @Override
    public GridView getGridView() {
        return mGridView;
    }

    @Override
    public TextView getSelectAllBtn() {
        return seleted_all;
    }

    @Override
    public TextView getShowNumTV() {
        return show_num;
    }

    @Override
    public Context getContext_() {
        return mContext;
    }


}
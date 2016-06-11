package com.example.myapplication.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myapplication.R;
import com.example.myapplication.widget.TitleBarView;

/**
 * Created by Administrator on 2016/5/30.
 */
public class OrderTimeFatherFragment extends Fragment{
    public static final String TAG ="MainActivity";
    private TitleBarView mTitleBarView;
    private Button btn_time;
    private Button btn_place;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_father,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mTitleBarView = (TitleBarView) view.findViewById(R.id.title_bar);
        mTitleBarView.getTitleLeft().performClick();
        mTitleBarView.setCommonTitle(View.VISIBLE, View.GONE, View.VISIBLE,
                View.VISIBLE);
        mTitleBarView.getTitleLeft().setEnabled(false);
        mTitleBarView.getTitleRight().setEnabled(true);

        FragmentTransaction ft = getFragmentManager()
                .beginTransaction();
        OrderTimeFragment timeFragment = new OrderTimeFragment();
        ft.replace(R.id.child_fragment, timeFragment);
        ft.commit();
        mTitleBarView.getTitleLeft().setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (mTitleBarView.getTitleLeft().isEnabled()) {
                    mTitleBarView.getTitleLeft().setEnabled(false);
                    mTitleBarView.getTitleRight().setEnabled(true);

                    FragmentTransaction ft = getFragmentManager()
                            .beginTransaction();
                    OrderTimeFragment timeFragment = new OrderTimeFragment();
                    ft.replace(R.id.child_fragment, timeFragment);
                    ft.commit();
                }
            }
        });

        mTitleBarView.getTitleRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTitleBarView.getTitleRight().isEnabled()) {
                    mTitleBarView.getTitleLeft().setEnabled(true);
                    mTitleBarView.getTitleRight().setEnabled(false);
                    FragmentTransaction ft = getFragmentManager()
                            .beginTransaction();
                    OrderPlaceFragment placeFragment = new OrderPlaceFragment();
                    ft.replace(R.id.child_fragment, placeFragment);
                    ft.commit();
                }
            }
        });
    }

}

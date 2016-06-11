package com.example.myapplication.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

/**
 * Created by Administrator on 2016/5/30.
 */
public class OrderPlaceFragment extends Fragment {
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("wmy","place");
        mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_place,container,false);
        return view;
    }
}

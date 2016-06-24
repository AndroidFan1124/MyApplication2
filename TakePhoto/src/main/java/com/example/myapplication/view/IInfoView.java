package com.example.myapplication.view;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/12.
 */
public interface IInfoView {


    public TextView getTv_tag_aperture() ;

    public TextView getTv_dateTime() ;

    public TextView getTv_falsh();

    public TextView getTv_tag_iso() ;

    public TextView getTv_make();

    public TextView getTv_tag_model();

    public TextView getTv_whitebalance();

    public TextView getTv_size();

    public EditText getEt_place() ;

    public EditText getEt_exif_name() ;

    public EditText getEt_theme();

    public EditText getEt_exif_note() ;

    public EditText getEt_exif_taker() ;
}

package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Picture;
import com.example.myapplication.util.FileSizeUtil;

public class InfoActivity extends Activity implements View.OnClickListener {


    TextView tv_tag_aperture;//光圈
    TextView tv_dateTime;//日期时间
    TextView tv_falsh;//闪光灯
    TextView tv_tag_iso;//ISO
    //    TextView tv_make;//图片制造商
    TextView tv_tag_model;//设备型号
    TextView tv_whitebalance;//白平衡
    TextView tv_size;
    TextView tv_path;


    EditText et_place;
    EditText et_exif_name;
    //    EditText et_theme;
//    EditText et_exif_note;
    EditText et_exif_taker;

    Button btn_pic_update;
    Context mContext;
    Picture p;
//    InfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        mContext = this;
        initView();
        initEvents();
        initData();

    }

    private void initView() {
        tv_tag_aperture = (TextView) findViewById(R.id.tv_exif_aperture);
        tv_dateTime = (TextView) findViewById(R.id.tv_exif_date);
        tv_falsh = (TextView) findViewById(R.id.tv_exif_falsh);
        tv_tag_iso = (TextView) findViewById(R.id.tv_exif_iso);
//        tv_make = (TextView) findViewById(R.id.tv_exif_name);
        tv_tag_model = (TextView) findViewById(R.id.tv_exif_model);
        tv_whitebalance = (TextView) findViewById(R.id.tv_exif_whitebalance);
        tv_size = (TextView) findViewById(R.id.tv_size);
        tv_path = (TextView)findViewById(R.id.tv_path);

        et_place = (EditText) findViewById(R.id.et_exif_place);
        et_exif_name = (EditText) findViewById(R.id.et_exif_name);
        et_exif_taker = (EditText) findViewById(R.id.et_exif_taker);

        btn_pic_update = (Button) findViewById(R.id.btn_pic_update);

    }

    private void initEvents() {
        btn_pic_update.setOnClickListener(this);
    }

    private void initData() {
//        presenter= new InfoPresenter(this,mContext);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        p = (Picture) bundle.getSerializable("pic_info");
        setData(p);
    }

    private void setData(Picture p) {
        tv_tag_aperture.setText(p.getTag_aperture());
        tv_dateTime.setText(p.getDateTime());
        tv_falsh.setText(p.getFalsh());
        tv_tag_iso.setText(p.getTag_iso());
        tv_path.setText(p.getPath());
//         tv_make.setText(p.getMake());
        tv_tag_model.setText(p.getTag_model());
        tv_whitebalance.setText(p.getWhitebalance());
        tv_size.setText(FileSizeUtil.getFileOrFilesSize(p.getPath(), FileSizeUtil.SIZETYPE_MB) + "");

        et_place.setText("这什么都没有写");
        et_exif_name.setText("这什么都没有写");//截取图片的最后名称，
        et_exif_taker.setText("这什么都没有写");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pic_update:
//                presenter.update("");//这记得写更新的，如果修改了图片名字，则应该判读图片的后缀名是否修改了
                break;
            default:
                break;
        }
    }
}

package com.example.myapplication.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SimpleTreeListViewAdapter;
import com.example.myapplication.adapter.TreeListViewAdapter;
import com.example.myapplication.entity.FileBean;
import com.example.myapplication.treeutil.Fileutil;
import com.example.myapplication.treeutil.Node;
import com.example.myapplication.util.PopupWindowUtil;
import com.example.myapplication.util.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class OrderPlaceFragment extends Fragment {
    private Context mContext;
    private ListView listView;
    private Button btn_new;
    private SimpleTreeListViewAdapter mAdapter;
    private List<FileBean> mDatas;
    private TextView tv_beauty_pic;
    private TextView tv_pic_info;
    private TextView tv_take_photo;
    public static final int LOCAL_PICTURE_CODE = 1;
    public static final int TIME_PICTURE_CODE = 2;
    public static final int TAKE_PHOTO_CODE = 3;
    private String NAME = "";
    private String PATH = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("wmy", "place");
        mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_place, container, false);
        listView = (ListView) view.findViewById(R.id.id_listview);
        btn_new = (Button) view.findViewById(R.id.btn_new);

        initData();
        try {
            mAdapter = new SimpleTreeListViewAdapter<FileBean>(listView, mContext,
                    mDatas, 1);
            listView.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        initEvents();
        return view;
    }

    private void initData() {
        mDatas = new ArrayList<FileBean>();
    }

    private void initEvents() {
        // TODO Auto-generated method stub

        btn_new.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                // 填加一个根目录
                mAdapter.createRoot("测试");
            }
        });
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreenodeClickListener() {

            @Override
            public void onClick(final Node node, final int position) {
                // TODO Auto-generated method stub
                if (!(node.isLeaf())) {

                } else {
                    ToastUtil.showShort("点击了" + node.getName());//叶子节点
                    PopupWindowUtil popup = new PopupWindowUtil();
                    View v = popup.showPopupInfo(R.layout.popup_image, mContext);


                    tv_beauty_pic = (TextView) v.findViewById(R.id.tv_beauty_pic);
                    tv_pic_info = (TextView) v.findViewById(R.id.tv_pic_info);
                    tv_take_photo = (TextView) v.findViewById(R.id.tv_take_photo);

                    tv_pic_info.setText("从图库中选择");
                    tv_beauty_pic.setText("从时间列表中选择");
                    tv_take_photo.setVisibility(View.VISIBLE);

                    tv_pic_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            /* 开启Pictures画面Type设定为image */
                            intent.setType("image/*");
                            /* 使用Intent.ACTION_GET_CONTENT这个Action */
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            /* 取得相片后返回本画面 */
                            startActivityForResult(intent, LOCAL_PICTURE_CODE);
                        }
                    });

                    tv_beauty_pic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });

                    tv_take_photo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            String aa="";
                            String path = mAdapter.getPath(node,aa);
                            Log.d("wmy","拍照path1--->"+path);
                            Log.d("wmy","拍照path2--->"+Environment.getExternalStorageDirectory()+"/DCIM/"+path);

                            if (Fileutil.haveSd()) {
                                NAME = new Date(System.currentTimeMillis())+"";
                                String temp = Environment.getExternalStorageDirectory()+"/DCIM"+path;
                                PATH = temp+NAME;
                                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
                                        Uri.fromFile(
                                                new File(temp,NAME)
                                        ));
                                mAdapter.addExtraNode(position,NAME);
                            }
                            startActivityForResult(intentFromCapture, TAKE_PHOTO_CODE);
                        }
                    });
                }
            }

        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int arg2, long arg3) {
                // TODO Auto-generated method stub

                // AlertDialog dialog =
                final EditText et = new EditText(mContext);
                // DialogFragment
                new AlertDialog.Builder(mContext)
                        // .create()
                        .setTitle("添加文件夹").setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0,
                                                int position) {
                                // TODO Auto-generated method stub
                                mAdapter.addExtraNode(arg2, et.getText()
                                        .toString());
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                }).show();
                return true;
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==LOCAL_PICTURE_CODE ){
            Uri uri = data.getData();
            ContentResolver cr = mContext.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                Log.d("wmy","相册获得："+bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }else if(requestCode == TAKE_PHOTO_CODE){
            File temp = new File(PATH);

            Log.d("wmy","temp--->"+temp);
        }
    }






}

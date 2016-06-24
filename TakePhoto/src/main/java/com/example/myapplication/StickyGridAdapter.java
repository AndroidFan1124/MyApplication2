package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.entity.Picture;
import com.example.myapplication.stickygridheaders.StickyGridHeadersSimpleAdapter;
import com.example.myapplication.util.NativeImageLoader;
import com.example.myapplication.widget.MyImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** * Created by Administrator on 2016/5/17.
 */

public class StickyGridAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter {

    Context context;
    List<Picture> mGridList;
    GridView gridView;
    private LayoutInflater mInflater;
    private Point mPoint = new Point(0, 0);//用来封装ImageView的宽和高的对象
//    public ViewHolder viewHolder;
    //    private boolean onSeleted = false;
    private boolean isChange = false;
    private Map<Integer, Boolean> mSelectMap = new HashMap<Integer, Boolean>();
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;

    public StickyGridAdapter(Context context, List<Picture> girdList, GridView GridView, HashMap<Integer, Boolean> mSelectMap) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        Log.d("wmy", "mGridList:" + girdList);
        this.mSelectMap = mSelectMap;
        isSelected = new HashMap<Integer, Boolean>();
        this.gridView = GridView;
        this.mGridList = girdList;
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < mGridList.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        return mGridList.size();
    }

    @Override
    public Object getItem(int i) {
        return mGridList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            mViewHolder = new ViewHolder();
            mViewHolder.mImageView = (MyImageView) view.findViewById(R.id.grid_item);
            mViewHolder.checkBox = (CheckBox)view.findViewById(R.id.loc_check);
            view.setTag(mViewHolder);
            //用来监听ImageView的宽和高
            mViewHolder.mImageView.setOnMeasureListener(new MyImageView.OnMeasureListener() {
                @Override
                public void onMeasureSize(int width, int height) {
                    mPoint.set(width, height);
                }
            });

        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        Log.d("wmy","isChange:"+isChange);

        if (isChange) {
            mViewHolder.checkBox.setVisibility(View.VISIBLE);
        } else {
            mViewHolder.checkBox.setVisibility(View.GONE);
        }
        // 根据isSelected来设置checkbox的选中状况
        if (getIsSelected().get(i) == null) {
            mViewHolder.checkBox.setChecked(false);
        } else {
            mViewHolder.checkBox.setChecked(getIsSelected().get(i));
        }

        mViewHolder.checkBox.setChecked(mSelectMap.get(i) == null ? false
                : mSelectMap.get(i));


        String path = mGridList.get(i).getPath();
        mViewHolder.mImageView.setTag(path);

        Bitmap bitmap = NativeImageLoader.getInstance().loadNativeImage(path, mPoint, new NativeImageLoader.NativeImageCallBack() {
            @Override
            public void onImageLoader(Bitmap bitmap, String path) {
                ImageView mImageView = (ImageView) gridView.findViewWithTag(path);
                if (bitmap != null && mImageView != null) {
                    mImageView.setImageBitmap(bitmap);
                }
            }
        });
        if (bitmap != null) {
            mViewHolder.mImageView.setImageBitmap(bitmap);
        } else {
            mViewHolder.mImageView.setImageResource(R.mipmap.ic_launcher);
        }

        return view;
    }

    @Override
    public long getHeaderId(int position) {
        return mGridList.get(position).getSection();
    }

    /**
     * 返回头部View
     *
     * @param position    The position of the header within the adapter's header data
     *                    set.
     * @param convertView The old view to reuse, if possible. Note: You should check
     *                    that this view is non-null and of an appropriate type before
     *                    using. If it is not possible to convert this view to display
     *                    the correct data, this method can create a new view.
     * @param parent      The parent that this view will eventually be attached to.
     * @return
     */
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder headHolder;
        if (convertView == null) {
            headHolder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.header, parent, false);
            headHolder.mTextView = (TextView) convertView.findViewById(R.id.header);
            convertView.setTag(headHolder);
        } else
            headHolder = (HeaderViewHolder) convertView.getTag();
        headHolder.mTextView.setText(mGridList.get(position).getTime());
        return convertView;
    }

   public static class ViewHolder {
        public MyImageView mImageView;
        public CheckBox checkBox;

    }

    public static class HeaderViewHolder {
        public TextView mTextView;
    }

    public void updateData(boolean isChange) {
        this.isChange = isChange;
        notifyDataSetChanged();
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        StickyGridAdapter.isSelected = isSelected;
    }
}

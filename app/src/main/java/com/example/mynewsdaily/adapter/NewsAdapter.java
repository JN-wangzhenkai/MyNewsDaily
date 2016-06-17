package com.example.mynewsdaily.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.mynewsdaily.R;
import com.example.mynewsdaily.bean.Bean;
import com.example.mynewsdaily.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private List<NewsBean> mData = new ArrayList<>();

    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;

    public NewsAdapter(Context context, ImageLoader imageLoader) {
        mImageLoader=imageLoader;
        mInflater = LayoutInflater.from(context);
    }

    public void addData( List<NewsBean> bean) {
        mData.addAll(bean);
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public NewsBean getItem(int position) {
        if (mData != null) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.news_list, null);
            viewHolder = new ViewHolder();

            //获得到NetworkImageView控件的实例
            viewHolder.icon = (NetworkImageView) convertView.findViewById(R.id.news_img1);

            viewHolder.title = (TextView) convertView.findViewById(R.id.new_tv_title);
            viewHolder.desc = (TextView) convertView.findViewById(R.id.news_tv_desc);
            viewHolder.time= (TextView) convertView.findViewById(R.id.news_tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewsBean bean = mData.get(position);

       // 设置加载中显示的图片
        viewHolder.icon.setDefaultImageResId(R.drawable.defaultpic);
        //设置加载失败时显示的图片
        viewHolder.icon.setErrorImageResId(R.mipmap.ic_launcher);
        //设置图片 第一个参数用于指定图片的URL地址，第二个参数则是前面创建好的ImageLoader对象
       viewHolder.icon.setImageUrl(bean.getIcon(), mImageLoader);

        viewHolder.title.setText(bean.getTitle());
        viewHolder.desc.setText(bean.getSummary());
        viewHolder.time.setText(bean.getStamp());

     //   Log.d("000000000000", "getView: "+bean.getLink());
        return convertView;
    }

    private class ViewHolder {
      NetworkImageView icon;
        TextView         title;
        TextView         desc;
        TextView time;
    }
}
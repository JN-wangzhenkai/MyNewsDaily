package com.example.mynewsdaily.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.mynewsdaily.R;
import com.example.mynewsdaily.adapter.NewsAdapter;
import com.example.mynewsdaily.bean.NewsBean;
import com.example.mynewsdaily.dao.NewsDBManager;
import com.example.mynewsdaily.utils.BitmapUtils;

import java.util.ArrayList;

/**
 *收藏新闻界面
 */
public class FragmentFavorite extends Fragment {
    private View view;
    private ListView listView;
    private NewsAdapter adapter;
   private ImageLoader imageLoader;
    private RequestQueue mQueue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_favorite,container,false);

        listView=(ListView) view.findViewById(R.id.lv_fragfavorite);

        adapter=new NewsAdapter(getActivity(),imageLoader);
        //创建一个RequestQueue 对象
        mQueue= Volley.newRequestQueue(getActivity());

        //创建一个 ImageLoader 对象  第二个参数传入BitmapUtils的实例
        imageLoader=new ImageLoader(mQueue,new BitmapUtils());


        //加载数据库
        loadLoveNews();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(itemListener);

        return view;
    }
    /**从数据库中加载保存的新闻*/
    private void loadLoveNews() {
        ArrayList<NewsBean> data=new NewsDBManager(getActivity()).queryLoveNews();
        adapter.addData(data);

        Log.d("要添加的数据……", "loadLoveNews: "+data);
    }

    private AdapterView.OnItemClickListener itemListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            // 打开显示当前选中的新闻
            NewsBean news = (NewsBean) parent.getItemAtPosition(position);
            Intent intent=new Intent(getActivity(), WebActivity.class);
            intent.putExtra("newsitem", news);
            getActivity().startActivity(intent);
        }
    };

}

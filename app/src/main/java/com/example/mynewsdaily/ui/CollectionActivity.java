package com.example.mynewsdaily.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.mynewsdaily.R;
import com.example.mynewsdaily.adapter.NewsAdapter;
import com.example.mynewsdaily.bean.NewsBean;
import com.example.mynewsdaily.dao.NewsDBManager;
import com.example.mynewsdaily.utils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;


public class CollectionActivity extends AppCompatActivity {

    private ListView            mListView;
    private ArrayList<NewsBean> mData;
    private NewsAdapter         mAdapter;
    private ImageLoader mImageLoader;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);


        mListView= (ListView) findViewById(R.id.lv_collection);

        mQueue = Volley.newRequestQueue(getApplicationContext());
        mImageLoader = new ImageLoader(mQueue, new BitmapUtils());
        mAdapter=new NewsAdapter(this,mImageLoader);
        loadLoveNews();
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 打开显示当前选中的新闻
                NewsBean news = (NewsBean) parent.getItemAtPosition(position);
                Intent intent=new Intent(CollectionActivity.this, WebActivity.class);
                intent.putExtra("newsitem", news);
               startActivity(intent);
            }
        });
    }

    /**从数据库中加载保存的新闻*/
    private void loadLoveNews() {
        mData=new NewsDBManager(getApplicationContext()).queryLoveNews();
        mAdapter.addData(mData);
    }


}

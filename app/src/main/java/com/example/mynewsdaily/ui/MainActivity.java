package com.example.mynewsdaily.ui;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.mynewsdaily.R;
import com.example.mynewsdaily.adapter.NewsAdapter;
import com.example.mynewsdaily.bean.NewsBean;
import com.example.mynewsdaily.utils.BitmapUtils;
import com.example.mynewsdaily.utils.DataParse;
import com.example.mynewsdaily.utils.GetData;
import com.example.mynewsdaily.utils.HttpUtils;
import com.example.mynewsdaily.utils.ParseNews;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Context mContext;

    private ListView mListView;
    private NewsAdapter mAdapter;
    private Gson gson;
    private ImageLoader mImageLoader;
    private RequestQueue mQueue;
    private List<NewsBean> mData;
    private static final String path = "http://118.244.212.82:9092/" +
            "newsClient/news_list?ver=1&subid=1&dir=1&nid=5&stamp=20140321&cnt=20";
    private FragmentLeft fragmentLeft;
    private FragmentRight fragmentRight;
    private static SlidingMenu menu;
    private FragmentFavorite fragmentFavorite;
    private Fragment fragmentLogin,fragmentRegister;
    private TextView textView_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建一个RequestQueue 对象
        mQueue = Volley.newRequestQueue(getApplicationContext());

        //创建一个 ImageLoader 对象  第二个参数传入BitmapUtils的实例
        mImageLoader = new ImageLoader(mQueue, new BitmapUtils());

        mAdapter = new NewsAdapter(this, mImageLoader);

        mListView = (ListView) findViewById(R.id.news_list);

        textView_title = (TextView) findViewById(R.id.textView1);

        //侧滑方法
        initSlidingMenu();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //接受Json数据
                String mJson = (String) msg.obj;
                //解析数据
                List<NewsBean> data = ParseNews.parserNewsList(mJson);

                //添加数据
                mAdapter.addData(data);

                Log.d("获取到的数据", "handleMessage: " + data);

                mListView.setAdapter(mAdapter);

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {

                //获取网络数据  并发送到主线程
                String info = HttpUtils.getJsonData(path);
                //   Log.d("11111111111111111", "run: "+info);
                Message msg = new Message();
                msg.obj = info;
                handler.sendMessage(msg);

            }
        }).start();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //获得当前选中的条目对象 传过去，便于收藏
                NewsBean news = (NewsBean) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                //利用intent 传递数据
                intent.putExtra("newsitem", news);//news 是serializable 值
                // intent.putExtra("url",mAdapter.getItem(position).getLink());
                startActivity(intent);
            }
        });
    }


    private void initSlidingMenu() {
        fragmentLeft = new FragmentLeft();
        fragmentRight = new FragmentRight();

        menu = new SlidingMenu(this);
        //设置侧滑菜单的位置，可选值LEFT , RIGHT , LEFT_RIGHT （两边都有菜单时设置）
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式，可选只MARGIN , CONTENT
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //把滑动菜单添加进所有的Activity中，可选值SLIDING_CONTENT ， SLIDING_WINDOW
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置SlidingMenu离屏幕的偏移量 即宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        //设置宽度
        // 或者 menu.setBehindWidth();

        //为侧滑菜单设置布局
        menu.setMenu(R.layout.layout_menu);
        //设置右边（二级）侧滑菜单
        menu.setSecondaryMenu(R.layout.layout_menu_right);

        //加载左侧 fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_menu, fragmentLeft).commit();
        //加载右侧fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_menu_right, fragmentRight).commit();
    }

    /**
     * 显示:“显示收藏新闻列表的Fragment”
     */
    public void showFragmentFavorite() {
        setTitle("收藏新闻");
        menu.showContent();
        if (fragmentFavorite == null)
            fragmentFavorite = new FragmentFavorite();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_content, fragmentFavorite).commit();
    }

    /**
     * 显示:“登录的Fragment”
     */
    public void showFragmentLogin() {
        setTitle("用户登录");
        menu.showContent();
        if (fragmentLogin == null)
            fragmentLogin = new FragmentLogin();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_content, fragmentLogin).commit();
    }

    /**
     * 显示:“注册的Fragment”
     */
    public void showFragmentRegister() {
        setTitle("用户注册");
        if (fragmentRegister == null)
            fragmentRegister = new FragmentRegister();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_content, fragmentRegister).commit();
    }
    /**
     * 更换当前界面的 Title
     */
    private void setTitle(String title) {
        textView_title.setText(title);
    }
}

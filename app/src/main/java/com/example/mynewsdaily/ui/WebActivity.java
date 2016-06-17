package com.example.mynewsdaily.ui;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewsdaily.R;
import com.example.mynewsdaily.adapter.NewsAdapter;
import com.example.mynewsdaily.bean.NewsBean;
import com.example.mynewsdaily.dao.NewsDBManager;

/**
 * webview 展示新闻界面
 */
public class WebActivity extends AppCompatActivity{

    private WebView webView;
    private ImageView imageViewMenu;

    /*PopupWindow这个类用来实现一个弹出框，可以使用任意布局的View作为其内容，
    是悬浮在当前activity之上的。
    */
    private PopupWindow popupWindow;

    private  NewsAdapter mAdapter;
    private NewsBean newsItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //获得传过来的条目网址
        Intent intent=getIntent();
        newsItem= (NewsBean) getIntent().getSerializableExtra("newsitem");

        webView= (WebView) findViewById(R.id.webview);
        imageViewMenu= (ImageView) findViewById(R.id.web_img_menu);

        WebSettings settings=webView.getSettings();
        //支持javaScript
        settings.setJavaScriptEnabled(true);
        //设置可以支持缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        settings.setDisplayZoomControls(false);

        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        settings.setLoadWithOverviewMode(true);

       webView.loadUrl(newsItem.getLink());

        imageViewMenu.setOnClickListener(imgclickListener);

        initPopuWindow();
    }

    private void initPopuWindow() {

        View popuview=getLayoutInflater().inflate(R.layout.item_pop_save,null);
        popupWindow=new PopupWindow(popuview, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT );
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        TextView tv_savelocal= (TextView) popuview.findViewById(R.id.saveLocal);
        tv_savelocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

                NewsDBManager manager=new NewsDBManager(WebActivity.this);
                if(manager.saveLoveNews(newsItem)){

                    Toast.makeText(WebActivity.this, "收藏成功！\n在主界面侧滑菜单中查看", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(WebActivity.this, "已经收藏过这条新闻了！\n在主界面侧滑菜单中查看", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private  View.OnClickListener imgclickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.web_img_back:
                    finish();
                    break;
                case R.id.web_img_menu:
                    if(popupWindow!=null&&popupWindow.isShowing()){
                        popupWindow.dismiss();
                    }else if(popupWindow != null){
                        popupWindow.showAsDropDown(imageViewMenu, 0, 12);
                    }
                  break;
            }
        }
    };
}

package com.example.mynewsdaily.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mynewsdaily.R;

import java.util.ArrayList;
import java.util.List;

public class LeadActivity extends AppCompatActivity {

    ViewPager viewPager;
    private List<View> viewList;

    private ImageView icons[] = new ImageView[4];
    private String leadActivity= "leadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences=getSharedPreferences(leadActivity,MODE_PRIVATE);
        //从文件中获取存储的数据，默认为true
        boolean isFirst=preferences.getBoolean("isFirstRun",true);

        //如果不是第一次打开，则直接跳转到Logo界面

        if (!isFirst) {
            Intent intent = new Intent(LeadActivity.this, LogoActivity.class);
            startActivity(intent);
            finish();
            return;
        } else {

            setContentView(R.layout.activity_lead);

            initView();
        }


    }

    private void initView() {
        icons[0] = (ImageView) findViewById(R.id.img_01);
        icons[1] = (ImageView) findViewById(R.id.img_02);
        icons[2] = (ImageView) findViewById(R.id.img_03);
        icons[3] = (ImageView) findViewById(R.id.img_04);

        //开始第一个圆点设为红色
        icons[0].setImageResource(R.drawable.a4);


        viewList = new ArrayList<>();

        viewList.add(getLayoutInflater().inflate(R.layout.lead1, null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead2, null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead3, null));
        viewList.add(getLayoutInflater().inflate(R.layout.lead4, null));

        viewPager= (ViewPager) findViewById(R.id.vp_show);
        viewPager.setAdapter(new MyPagerAdapter(viewList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //界面切换时调用
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
//界面切换后调用
            @Override
            public void onPageSelected(int position) {
                if (position >= icons.length-1) {

                    Intent intent = new Intent(LeadActivity.this, LogoActivity.class);
                    startActivity(intent);
                    finish();

                    //保存第一次运行的数据
                    SharedPreferences pre = getSharedPreferences(leadActivity, Context.MODE_PRIVATE);

                    SharedPreferences.Editor edit = pre.edit();

                    edit.putBoolean("isFirstRun", false);

                    edit.commit();


                }
                for (int i = 0; i < icons.length; i++) {

                    icons[i].setImageResource(R.drawable.userbg);

                }
                //滑动到 哪个位置 就把该位置的圆点 改设为红色

                icons[position].setImageResource(R.drawable.a4);

            }
//滑动状态变化时调用
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyPagerAdapter extends PagerAdapter {

        private List<View> list;
        public MyPagerAdapter(List<View> list){
            this.list=list;
        }



        //初始化position 展现到页面上来

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position), 0);

            return list.get(position);
        }

      //  设置控件中显示界面的数量
        @Override
        public int getCount() {

            return list.size();
        }

        //当不可见时销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

       // 判断是否由对象生成界面
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}

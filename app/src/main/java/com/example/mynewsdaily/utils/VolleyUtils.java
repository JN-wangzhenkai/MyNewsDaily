package com.example.mynewsdaily.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by wangzhenkai on 2016/6/12.
 * 获取网络数据
 */
public class VolleyUtils {
    public static RequestQueue mQueue;
     Context mContext;

    public VolleyUtils(Context mContext) {
        if (mQueue == null) {
            //构造函数里 获取requestQueue 对象
            mQueue = Volley.newRequestQueue(mContext);
        }
        this.mContext = mContext;
    }


    public  void getJsonData(String url) {

        //为了发出一条http 请求需要创建一个StringRquest 对象
        //三个参数 统一资源定位器，正确回调，错误回调
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("111111", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("3333333", "onErrorResponse: " + "3333333333");
            }
        });
        //StringRequest  对象添加到 RequestQueue里
        mQueue.add(request);
    }

}

package com.example.mynewsdaily.utils;

import android.util.Log;

import com.example.mynewsdaily.bean.Bean;
import com.example.mynewsdaily.bean.NewsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


/**
 * Created by wangzhenkai on 2016/6/12.
 */
public class DataParse {

    public static List<NewsBean> parserNewsList(String json){

        Log.d("TAG", "parserNewsList : " + json);
        Gson gson = new Gson();
       Bean <List<NewsBean>> newsBean = gson.fromJson(json, new TypeToken<Bean<List<NewsBean>>>() {}.getType());

        return newsBean.getData();
    }
}

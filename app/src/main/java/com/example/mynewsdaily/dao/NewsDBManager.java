package com.example.mynewsdaily.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mynewsdaily.bean.NewsBean;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by wangzhenkai on 2016/6/15.
 */
public class NewsDBManager {
    private DBOpenHelper dbHelper;
    private Context mContext;

    public NewsDBManager(Context context) {
        this.mContext = context;
        dbHelper = new DBOpenHelper(context);
    }

    /*添加数据*/

    public void insertNews(NewsBean news){

        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("type",news.getType());
        values.put("nid",news.getNid());
        values.put("title",news.getTitle());
        values.put("summary",news.getSummary());
        values.put("icon",news.getIcon());
        values.put("link",news.getLink());
        values.put("stamp",news.getStamp());

       db.insert("news",null,values);
        db.close();
    }

    /*查询数据*/
    public ArrayList<NewsBean>queryNews(int count,int offset){

        ArrayList<NewsBean> newsList=new ArrayList<>();

        SQLiteDatabase db=dbHelper.getWritableDatabase();

        String sql="select * from news order by _id desc limit "+count+" offset "+offset;
        Cursor cursor=db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                int nid = cursor.getInt(cursor.getColumnIndex("nid"));
                String stamp = cursor.getString(cursor.getColumnIndex("stamp"));
                String icon = cursor.getString(cursor.getColumnIndex("icon"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String summary = cursor.getString(cursor.getColumnIndex("summary"));
                String link = cursor.getString(cursor.getColumnIndex("link"));

                NewsBean news = new NewsBean();

                newsList.add(news);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }

        return newsList;

    }

    /**
     * 收藏新闻
     */
    public boolean saveLoveNews(NewsBean news){
        try {
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            //查询要收藏的那一条
            Cursor cursor=db.rawQuery("select * from lovenews where nid="+news.getNid(),null);
            if(cursor.moveToFirst()){
                cursor.close();
                return false;
            }
            cursor.close();
            ContentValues values=new ContentValues();
            values.put("type", news.getType());
            values.put("nid", news.getNid());
            values.put("stamp", news.getStamp());
            values.put("icon", news.getIcon());
            values.put("title", news.getTitle());
            values.put("summary", news.getSummary());
            values.put("link", news.getLink());

            db.insert("lovenews", null, values);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取收藏新闻的列表
     * @return 新闻的 列表
     */
    public ArrayList<NewsBean> queryLoveNews(){
        ArrayList<NewsBean> newsList=new ArrayList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String sql="select * from lovenews ";
        Cursor cursor=db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                int nid = cursor.getInt(cursor.getColumnIndex("nid"));
                String stamp = cursor.getString(cursor.getColumnIndex("stamp"));
                String icon = cursor.getString(cursor.getColumnIndex("icon"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String summary = cursor.getString(cursor.getColumnIndex("summary"));
                String link = cursor.getString(cursor.getColumnIndex("link"));

                NewsBean news = new NewsBean();
                news.setIcon(icon);
                news.setLink(link);
                news.setSummary(summary);
                news.setTitle(title);
                news.setType(type);
                news.setStamp(stamp);
                news.setNid(nid);

                newsList.add(news);
                Log.d("哈哈哈哈哈哈哈哈", "queryLoveNews: "+newsList);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return newsList;
    }
}

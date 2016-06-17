package com.example.mynewsdaily.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
  自定义类继承SQLiteOpenHelper
 */
public class DBOpenHelper extends SQLiteOpenHelper{

    //context:上下文 name:要创建的数据库名称 factory:用来创建cursor对象 默认为null version:数据库版本
    // 其他三个可以写死只传入 context
    public DBOpenHelper(Context context ) {
        super(context,"newsdb.db",null,1);
    }

    //第一次创建数据库时执行 适合做表结构的初始化
    @Override
    public void onCreate(SQLiteDatabase db) {
         //创建三个表
        //type 表_id 主键自增长 subid  subgrou
        db.execSQL("create table type(_id integer primary key autoincrement,subid integer,subgrop text)");
        db.execSQL("create table news(_id integer primary key autoincrement,type integer,nid integer,stamp text,icon text,title text,summary text,link text)");
        db.execSQL("create table lovenews(_id integer primary key autoincrement,type integer,nid integer,stamp text,icon text,title text,summary text,link text)");
    }

    //版本号升级时执行，适合做表结构的更改
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

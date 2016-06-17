package com.example.mynewsdaily.utils;

import android.graphics.Bitmap;
import android.util.LruCache;


import com.android.volley.toolbox.ImageLoader;

/**
 * 图片缓存类
 */
public class BitmapUtils  implements ImageLoader.ImageCache{

    //借助Android提供的LruCache功能
    private LruCache<String, Bitmap> mCache;

    public BitmapUtils() {
        //设置图片最大值 10M
        int maxSize = 10 * 1024 * 1024;
        mCache=new LruCache<String,Bitmap>(maxSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();//位图每一行所占用的内存字节数*位图的高度
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

        mCache.put(url,bitmap);
    }
}

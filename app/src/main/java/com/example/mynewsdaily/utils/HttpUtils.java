package com.example.mynewsdaily.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *获取网络数据
 */
public class HttpUtils {

    private static String data=null;

    public static String  getJsonData(String path){

        try {
            URL url=new URL(path);
            // 打开链接获取 httpurlconnection 的对象
            HttpURLConnection  urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");//设置超时时间
            urlConnection.setConnectTimeout(5000);//设置请求方式

            InputStream in=urlConnection.getInputStream();//打开输入流

            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            byte[]bytes=new byte[1024];

            int len=0;
            while ((len = in.read(bytes)) != -1) {

                outputStream.write(bytes, 0, len);
            }

            outputStream.close();
            in.close();

            data = outputStream.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}

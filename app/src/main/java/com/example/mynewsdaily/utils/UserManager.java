package com.example.mynewsdaily.utils;


import android.content.Context;
import android.util.Log;

import com.android.volley.Response;

public class UserManager {
    private static  UserManager userManager;
    private Context context;
    private String imei;

    public UserManager(Context context) {
        this.context = context;
        imei=SystemUtils.getIMEI(context);
    }

    public static UserManager getInstance(Context context){
        if(userManager==null){
            userManager=new UserManager(context);
        }
        return userManager;
    }

    /** 注册
     * user_register?ver=版本号&uid=用户名&email=邮箱&pwd=登陆密码
     * @param args
     *            包含参数如下： ver : 版本 uid : 用户昵称 pwd : 密码 email : 邮箱
     */

    public void register(Context context,String...args){
        Log.d("每日新闻", "执行注册: ");

        // 1.提交到服务器端 生成用户
        new VolleyUtils(context).getJsonData(CommonUtil.APPURL
                +"/user_register?ver="+args[0]+"&uid="+args[1]
                +"&pwd="+args[2]+"&email="+args[3]);
    }

    /**
     * 、http://118.244.212.82:9094//newsClient/login?uid=admin&pwd=admin&imei=
     * abc&ver=1&device=1
     * http://118.244.212.82:9094//newsClient/login?ver=1&uid=
     * admin&pwd=admin&device=000000000000000 用户登录处理方法
     * @param args
     *            包含参数如下： ver : 版本 uid : 用户昵称 pwd : 密码 imei : 手机IMEI号 device :
     *            登录设备 ： 0 为移动端 ，1为PC端
     */

    public void login(Context context,String...args){

        new VolleyUtils(context).getJsonData(CommonUtil.APPURL
                + "/user_login?ver=" + args[0] + "&uid=" + args[1] + "&pwd="
                + args[2] + "&device=" + args[3]);
    }

    public void forgetPass(Context context, String... args) {
        Log.d("忘记密码……", "ִ执行忘记密码...");

        new VolleyUtils(context).getJsonData(CommonUtil.APPURL
                        + "/user_forgetpass?ver=" + args[0] + "&email=" + args[1]
               );
    }
}

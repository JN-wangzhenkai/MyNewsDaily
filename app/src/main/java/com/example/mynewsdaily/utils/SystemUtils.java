package com.example.mynewsdaily.utils;


import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;

public class SystemUtils {

    private static SystemUtils  systemUtils;

    private Context context;

    private TelephonyManager telephonyManager;
    private ConnectivityManager connectivityManager;
    private LocationManager locationManager;
    private String position;


    public SystemUtils(Context context) {
        this.context = context;
        telephonyManager= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        locationManager= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    }

    public SystemUtils getInstance(Context context){
        if(systemUtils==null){
           systemUtils=new SystemUtils(context);
        }
        return systemUtils;
    }

    //判断网络是否连接

    public boolean isNetConnect(){
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if (info!=null&&info.isConnected()){
            return true;
        }
        return false;
    }

    //获取sim卡类型
    public String simType(){
        String simOperator=telephonyManager.getSimOperator();
        String type="";

        if("46000".equals(simOperator)){
            type="移动";
        } else if("46002".equals(simOperator)){
            type="移动";

        }else if("46001".equals(simOperator)){
            type="联通";
        }else if("46003".equals(simOperator)){
            type="电信";
        }
        return type;
    }

    /**
     * 获取手机IMEI 号码
     * @return IMEI
     */
    public String getIMEI(){
        return telephonyManager.getDeviceId();
    }

    public String getPosition(){
        return this.position;
    }

    /**
     * 获取手机的IMEI值ֵ
     */

    public static String getIMEI(Context context){
        TelephonyManager telephonyManager= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}

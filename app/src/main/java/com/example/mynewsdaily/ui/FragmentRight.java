package com.example.mynewsdaily.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewsdaily.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 右边侧拉界面
 */
public class FragmentRight extends Fragment {

    private View view;
    private Button btnShare;
    private SharedPreferences preferences;
    private RelativeLayout relativeLayout_login;
    private RelativeLayout user;
    private ImageView img_login;
    private TextView tv_login;
    private TextView updateTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_right,null);

        preferences = getActivity().getSharedPreferences("", Context.MODE_PRIVATE);
        boolean islogin=preferences.getBoolean("",false);


        relativeLayout_login = (RelativeLayout) view.findViewById(R.id.relativelayout_login);
        user = (RelativeLayout) view.findViewById(R.id.relativelayout_user);
        img_login = (ImageView) view.findViewById(R.id.img_login);
        tv_login = (TextView) view.findViewById(R.id.tv_login);
        updateTv = (TextView) view.findViewById(R.id.update_version);

        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showFragmentLogin();
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        relativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //版本更新
        updateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        //一键分享
        btnShare= (Button) view.findViewById(R.id.btn_share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        return view;
    }

    //一键分享
    private void showShare(){

        ShareSDK.initSDK(getActivity());

        OnekeyShare oks=new OnekeyShare();
        //关闭sso 授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("Tower新闻客户端");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("Tower新闻客户端是一款好的新闻软件");

        // 启动分享GUI
        oks.show(getActivity());

    }
}

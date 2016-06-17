package com.example.mynewsdaily.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynewsdaily.R;


public class FragmentLogin extends Fragment {
    private View view;
    private EditText editNickname;
    private EditText editpwd;
    private Button btn_register;
    private Button btn_forgetpwd;
    private Button btn_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_login,null);

        editNickname = (EditText) view.findViewById(R.id.editText_nickname);
        editpwd = (EditText) view.findViewById(R.id.edit_pwd);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_forgetpwd = (Button) view.findViewById(R.id.btn_forget_pwd);
        btn_login = (Button) view.findViewById(R.id.btn_login);

        //注册
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //忘记密码
        btn_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editNickname.getText().toString().trim();
                String pwd=editpwd.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getActivity(), "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.length()<6||pwd.length()>16){
                    Toast.makeText(getActivity(), "密码长度错误", Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });

        return view;
    }
}

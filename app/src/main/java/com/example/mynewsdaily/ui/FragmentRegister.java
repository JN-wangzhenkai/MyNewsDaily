package com.example.mynewsdaily.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.mynewsdaily.R;
import com.example.mynewsdaily.utils.CommonUtil;
import com.example.mynewsdaily.utils.UserManager;

/**
 * Created by wangzhenkai on 2016/6/16.
 */
public class FragmentRegister extends Fragment {

    private View view ;
   private UserManager userManager;
    private EditText editRegistEmail;
    private EditText editRegistName;
    private EditText editRegistPwd;
    private Button btn_register;
    private CheckBox checkBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_favorite,null);

        editRegistEmail = (EditText) view.findViewById(R.id.edit_register_email);
        editRegistName = (EditText) view.findViewById(R.id.edit_register_name);
        editRegistPwd = (EditText) view.findViewById(R.id.edit_register_pwd);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox1);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox.isChecked()){
                    Toast.makeText(getActivity(), "没有同意协议条款", Toast.LENGTH_SHORT).show();
                   return;
                }

                String email=editRegistEmail.getText().toString().trim();
                String name=editRegistName.getText().toString().trim();
                String pwd=editRegistPwd.getText().toString().trim();

                if(!CommonUtil.verifyEmail(email)){
                    Toast.makeText(getActivity(), "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                 return;
                }

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getActivity(), "请输入用户昵称", Toast.LENGTH_SHORT).show();
                   return;
                }
                if(pwd.length()<6||pwd.length()>16){
                    Toast.makeText(getActivity(), "密码长度错误", Toast.LENGTH_SHORT).show();
                  return;
                }
                if(!CommonUtil.verifyPassword(pwd)){
                    Toast.makeText(getActivity(), "请输入6-22位数字和字母组合的密码", Toast.LENGTH_SHORT).show();
                  return;
                }

                if (userManager == null)
                { userManager = UserManager.getInstance(getActivity());}

                userManager.register(getActivity(),CommonUtil.VERSION_CODE + "",name,pwd,email);
            }
        });
        return view ;
    }



}

package com.fstech.yzedutc.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.activity.LoginActivity;
import com.fstech.yzedutc.activity.SettingActivity;
import com.fstech.yzedutc.activity.UserInfoActivity;
import com.fstech.yzedutc.activity.WalletActivity;
import com.fstech.yzedutc.application.YZEduApplication;
import com.fstech.yzedutc.util.ImageUitl;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

/**
 * Created by shaoxin on 18-3-25.
 * 个人界面的Fragment
 */

public class PersonFragment extends Fragment implements View.OnClickListener {

    // 定义UI对象
    private RelativeLayout re_wallet, re_setting,re_quit;
    private TextView tv_name;
    private QMUIRadiusImageView iv_avatar;
    private ImageView iv_message;
    private YZEduApplication application;
    SharedPreferences perPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        re_wallet = (RelativeLayout) getActivity().findViewById(R.id.re_wallet);
        re_setting = (RelativeLayout) getActivity().findViewById(R.id.re_setting);
        re_quit = (RelativeLayout)getActivity().findViewById(R.id.re_quit);
        iv_avatar = (QMUIRadiusImageView) getActivity().findViewById(R.id.iv_avatar);
        iv_message = (ImageView) getActivity().findViewById(R.id.iv_message);
        tv_name = (TextView) getActivity().findViewById(R.id.tv_name);

        re_wallet.setOnClickListener(this);
        re_setting.setOnClickListener(this);
        re_quit.setOnClickListener(this);
        iv_avatar.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        tv_name.setOnClickListener(this);
    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        application = (YZEduApplication) getActivity().getApplication();
        if (application.getToken() != null) {
            if (application.getUserName() != null) {
                tv_name.setText(application.getUserName());
                final String img = application.getAvatar();
                // 显示图片
                ImageUitl.showNetImage(iv_avatar, img);
            }
        }

    }

    private boolean checkLogin() {
        if (application.getToken() == null) {
            Toast.makeText(getActivity(), R.string.please_login_first, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /*
    * 点击用户头像或用户名的方法
    * 如果已登录进入用户主页
    * 否则进入登录页
    * */
    private void userMain() {
        if (application.getToken() != null) {
            Intent intent = new Intent(getActivity(), UserInfoActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    // 监听按钮事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_wallet:
                Log.e("click", "wallet");
                if (checkLogin() == true) {
                    Intent intent2 = new Intent(getActivity(), WalletActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.re_setting:
                Log.e("click", "setting");
                Intent intent4 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent4);
                break;
            case R.id.iv_avatar:
                Log.e("click", "avatar");
                userMain();
                break;
            case R.id.tv_name:
                Log.e("click", "name");
                userMain();
                break;
            case R.id.re_quit:
                Log.e("click","quit");
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                clearLogin();
                startActivity(intent);
                getActivity().finish();
		break;
            default:
                break;
        }
    }

    /**
     * 清除登录缓存信息
     */
    private void clearLogin(){
        perPreferences = getActivity().getSharedPreferences("loginmsg", getActivity().MODE_PRIVATE);
        editor = perPreferences.edit();
        editor.putString("pass","");
        editor.commit();
    }

}

package com.fstech.yzedusc.fragment;

import android.content.Intent;
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

import com.fstech.yzedusc.R;
import com.fstech.yzedusc.activity.AbilityActivity;
import com.fstech.yzedusc.activity.DiscoverActivity;
import com.fstech.yzedusc.activity.LearnLikeActivity;
import com.fstech.yzedusc.activity.LoginActivity;
import com.fstech.yzedusc.activity.MessageActivity;
import com.fstech.yzedusc.activity.SettingActivity;
import com.fstech.yzedusc.activity.UserInfoActivity;
import com.fstech.yzedusc.activity.WalletActivity;
import com.fstech.yzedusc.application.YZEduApplication;
import com.fstech.yzedusc.util.DownloadTools;
import com.fstech.yzedusc.util.ImageUitl;
import com.fstech.yzedusc.util.ThreadUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

/**
 * Created by shaoxin on 18-3-25.
 * 个人界面的Fragment
 */

public class PersonFragment extends Fragment implements View.OnClickListener {

    // 定义UI对象
    private RelativeLayout re_wallet, re_ability, re_setting, re_learn_like, re_discover;
    private TextView tv_name;
    private QMUIRadiusImageView iv_avatar;
    private ImageView iv_message;
    private YZEduApplication application;

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
        re_ability = (RelativeLayout) getActivity().findViewById(R.id.re_ability);
        re_setting = (RelativeLayout) getActivity().findViewById(R.id.re_setting);
        re_learn_like = (RelativeLayout) getActivity().findViewById(R.id.re_learn_like);
        re_discover = (RelativeLayout) getActivity().findViewById(R.id.re_discover);
        iv_avatar = (QMUIRadiusImageView) getActivity().findViewById(R.id.iv_avatar);
        iv_message = (ImageView) getActivity().findViewById(R.id.iv_message);
        tv_name = (TextView) getActivity().findViewById(R.id.tv_name);

        re_wallet.setOnClickListener(this);
        re_ability.setOnClickListener(this);
        re_setting.setOnClickListener(this);
        re_learn_like.setOnClickListener(this);
        re_discover.setOnClickListener(this);
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
                ThreadUtil.runInThread(new Runnable() {
                    @Override
                    public void run() {
                        int state = DownloadTools.downloadImg(img);
                        ThreadUtil.runInUIThread(new Runnable() {
                            @Override
                            public void run() {
                                ImageUitl.SimpleShowImage(img, iv_avatar);
                            }
                        });
                    }
                });
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
            case R.id.re_learn_like:
                Log.e("click", "learn_like");
                if (checkLogin() == true) {
                    Intent intent0 = new Intent(getActivity(), LearnLikeActivity.class);
                    startActivity(intent0);
                }
                break;
            case R.id.re_discover:
                Log.e("click", "discover");
                if (checkLogin() == true) {
                    Intent intent1 = new Intent(getActivity(), DiscoverActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.re_wallet:
                Log.e("click", "wallet");
                if (checkLogin() == true) {
                    Intent intent2 = new Intent(getActivity(), WalletActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.re_ability:
                Log.e("click", "ability");
                if (checkLogin() == true) {
                    Intent intent3 = new Intent(getActivity(), AbilityActivity.class);
                    startActivity(intent3);
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
            case R.id.iv_message:
                Log.e("click", "message");
                if (checkLogin() == true) {
                    Intent intent7 = new Intent(getActivity(), MessageActivity.class);
                    startActivity(intent7);
                }
                break;
            default:
                break;
        }
    }

}

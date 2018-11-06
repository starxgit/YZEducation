package com.fstech.yzedutc.fragment;

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

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.activity.AbilityActivity;
import com.fstech.yzedutc.activity.DiscoverActivity;
import com.fstech.yzedutc.activity.LearnLikeActivity;
import com.fstech.yzedutc.activity.MessageActivity;
import com.fstech.yzedutc.activity.SettingActivity;
import com.fstech.yzedutc.activity.UserInfoActivity;
import com.fstech.yzedutc.activity.WalletActivity;
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

        re_learn_like.setVisibility(View.GONE);
        re_discover.setVisibility(View.GONE);
        re_ability.setVisibility(View.GONE);

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

    }

    // 监听按钮事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_learn_like:
                Log.e("click", "learn_like");
                Intent intent0 = new Intent(getActivity(), LearnLikeActivity.class);
                startActivity(intent0);
                break;
            case R.id.re_discover:
                Log.e("click", "discover");
                Intent intent1 = new Intent(getActivity(), DiscoverActivity.class);
                startActivity(intent1);
                break;
            case R.id.re_wallet:
                Log.e("click", "wallet");
                Intent intent2 = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent2);
                break;
            case R.id.re_ability:
                Log.e("click", "ability");
                Intent intent3 = new Intent(getActivity(), AbilityActivity.class);
                startActivity(intent3);
                break;
            case R.id.re_setting:
                Log.e("click", "setting");
                Intent intent4 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent4);
                break;
            case R.id.iv_avatar:
                Log.e("click", "avatar");
                Intent intent5 = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent5);
                break;
            case R.id.tv_name:
                Log.e("click", "name");
                Intent intent6 = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent6);
                break;
            case R.id.iv_message:
                Log.e("click", "message");
                Intent intent7 = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent7);
                break;
            default:
                break;
        }
    }

}

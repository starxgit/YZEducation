package com.fstech.yzedutc.activity;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fstech.yzedutc.R;
import com.fstech.yzedutc.adapter.MyFragmentAdapter;
import com.fstech.yzedutc.util.CacheActivityUtil;
import com.fstech.yzedutc.util.Constant;
import com.fstech.yzedutc.util.PixelUtil;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    // 定义UI对象
    private RadioGroup rg_tab_bar;
    private RadioButton rb_main;
    private RadioButton rb_course;
    private RadioButton rb_school;
    private RadioButton rb_learn;
    private RadioButton rb_person;
    private ViewPager vpager;

    // 定义Fragment的适配器
    private MyFragmentAdapter mAdapter;

    // 定义退出时间
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        initView();
        changeImageSize();
        rb_main.setChecked(true);
    }

    // 按键事件
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                this.exitApp();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /*
    初始化，用于获取控件实例
    */
    private void initView() {
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        CacheActivityUtil.addActivity(MainActivity.this);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_main = (RadioButton) findViewById(R.id.rb_main);
        rb_course = (RadioButton) findViewById(R.id.rb_course);
        rb_school = (RadioButton) findViewById(R.id.rb_school);
        rb_learn = (RadioButton) findViewById(R.id.rb_learn);
        rb_person = (RadioButton) findViewById(R.id.rb_person);
        rg_tab_bar.setOnCheckedChangeListener(this);
        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main:
                vpager.setCurrentItem(Constant.PAGE_ONE);
                break;
            case R.id.rb_course:
                vpager.setCurrentItem(Constant.PAGE_TWO);
            break;
            case R.id.rb_learn:
                vpager.setCurrentItem(Constant.PAGE_THREE);
                break;
            case R.id.rb_school:
                vpager.setCurrentItem(Constant.PAGE_FOUR);
                break;
            case R.id.rb_person:
                vpager.setCurrentItem(Constant.PAGE_FIVE);
                break;
        }
    }

    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case Constant.PAGE_ONE:
                    rb_main.setChecked(true);
                    break;
                case Constant.PAGE_TWO:
                    rb_course.setChecked(true);
                    break;
                case Constant.PAGE_THREE:
                    rb_learn.setChecked(true);
                    break;
                case Constant.PAGE_FOUR:
                    rb_school.setChecked(true);
                    break;
                case Constant.PAGE_FIVE:
                    rb_person.setChecked(true);
                    break;
            }
        }
    }

    /*
    *这是设置底部图标大小的方法
    */
    private void changeImageSize() {
        final int len = PixelUtil.dip2px(MainActivity.this, 28);
        //定义底部标签图片大小
        Drawable drawable1 = getResources().getDrawable(R.drawable.menu_main);
        drawable1.setBounds(0, 0, len, len);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb_main.setCompoundDrawables(null, drawable1, null, null);//只放上面
        Drawable drawable2 = getResources().getDrawable(R.drawable.menu_course);
        drawable2.setBounds(0, 0, len, len);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb_course.setCompoundDrawables(null, drawable2, null, null);//只放上面
        Drawable drawable3 = getResources().getDrawable(R.drawable.menu_mycourse);
        drawable3.setBounds(0, 0, len, len);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb_learn.setCompoundDrawables(null, drawable3, null, null);//只放上面
        Drawable drawable4 = getResources().getDrawable(R.drawable.menu_personal);
        drawable4.setBounds(0, 0, len, len);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb_person.setCompoundDrawables(null, drawable4, null, null);//只放上面
        Drawable drawable5 = getResources().getDrawable(R.drawable.menu_school);
        drawable5.setBounds(0, 0, len, len);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        rb_school.setCompoundDrawables(null, drawable5, null, null);//只放上面
    }

    /**
     * 退出程序
     */
    private void exitApp() {
        // 判断2次点击事件时间
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


}
package com.fstech.yzedusc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.fstech.yzedusc.fragment.CourseFragment;
import com.fstech.yzedusc.fragment.LearnFragment;
import com.fstech.yzedusc.fragment.MainFragment;
import com.fstech.yzedusc.fragment.PersonFragment;
import com.fstech.yzedusc.fragment.SchoolFragment;
import com.fstech.yzedusc.util.Constant;

/**
 * Created by shaoxin on 18-3-25.
 * ViewPagerFragment的适配器类
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 5;
    private MainFragment myFragment0 = null;
    private CourseFragment myFragment1 = null;
    private LearnFragment myFragment2 = null;
    private SchoolFragment myFragment3 = null;
    private PersonFragment myFragment4 = null;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        myFragment0 = new MainFragment();
        myFragment1 = new CourseFragment();
        myFragment2 = new LearnFragment();
        myFragment3 = new SchoolFragment();
        myFragment4 = new PersonFragment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case Constant.PAGE_ONE:
                fragment = myFragment0;
                break;
            case Constant.PAGE_TWO:
                fragment = myFragment1;
                break;
            case Constant.PAGE_THREE:
                fragment = myFragment2;
                break;
            case Constant.PAGE_FOUR:
                fragment = myFragment3;
                break;
            case Constant.PAGE_FIVE:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

}
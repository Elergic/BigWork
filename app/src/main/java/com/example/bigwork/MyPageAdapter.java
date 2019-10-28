package com.example.bigwork;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"国内","国际","体育","财经","科技","军事"};

    public MyPageAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new FirstFragment();
        }else if (position == 1){
            return new SecondFragment();
        }else if (position == 2){
            return new ThirdFragment();
        }else if (position == 3){
            return new FourthFragment();
        }else if (position == 4){
            return new FifthFragment();
        }else {
            return new SixthFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return 6;
    }
}

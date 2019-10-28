package com.example.bigwork;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyListAdapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"国内","国际","体育","财经","科技","军事"};

    public MyListAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new FirstListFragment();
        }else if (position == 1){
            return new SecondListFragment();
        }else if (position == 2){
            return new ThirdListFragment();
        }else if (position == 3){
            return new FourthListFragment();
        }else if (position == 4){
            return new FifthListFragment();
        }else {
            return new SixthListFragment();
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

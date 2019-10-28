package com.example.bigwork;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ViewPager viewPager = findViewById(R.id.viewpager_list);
        MyListAdapter listAdapter = new MyListAdapter(getSupportFragmentManager());
        viewPager.setAdapter(listAdapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs_list);
        tabLayout.setupWithViewPager(viewPager);
    }
}

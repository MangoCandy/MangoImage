package com.mangocandy.mangoimage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.mangocandy.beans.Types;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Types types=new Types();


    List<Fragment> fragment_showTitles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);
        initFragment();


    }
    TabLayout tabLayout;
    ViewPager viewPager;
    public void initFragment(){
        tabLayout=(TabLayout)findViewById(R.id.typeslayout);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        for(int i=0;i<types.getTypes().length;i++){
            Fragment_showTitle fragmentShowTitle=null;
            fragment_showTitles.add(fragmentShowTitle);
        }
        viewPager.setAdapter(new Adapter_showTitle(getSupportFragmentManager(),fragment_showTitles));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(1);

    }
}

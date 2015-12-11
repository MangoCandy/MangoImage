package com.mangocandy.mangoimage;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.view.ViewGroup;
import com.mangocandy.beans.Types;

import java.util.List;

/**
 * Created by Administrator on 2015/12/4.
 */
public class Adapter_showTitle extends FragmentPagerAdapter {
    List<Fragment> fragments;
    Types types=new Types();
    public Adapter_showTitle(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return Fragment_showTitle.newInstance(types.getTypes()[position]);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return types.getTypes()[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}

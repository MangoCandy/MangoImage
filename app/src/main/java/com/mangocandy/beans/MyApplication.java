package com.mangocandy.beans;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2015/12/7.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}

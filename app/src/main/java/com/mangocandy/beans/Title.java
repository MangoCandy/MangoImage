package com.mangocandy.beans;

import android.graphics.Bitmap;

/**
 * Created by MangoCandy on 2015/12/6.
 */
public class Title {
    String title;
    String s_img;
    String l_img;
    Bitmap bitmap;
    String height;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getS_img() {
        return s_img;
    }

    public void setS_img(String s_img) {
        this.s_img = s_img;
    }

    public String getL_img() {
        return l_img;
    }

    public void setL_img(String l_img) {
        this.l_img = l_img;
    }
}

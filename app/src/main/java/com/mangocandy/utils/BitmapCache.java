package com.mangocandy.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Administrator on 2015/12/7.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> cache;
    public BitmapCache() {
        cache = new LruCache<String, Bitmap>(25*500*500) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }

        };
    }
    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url,bitmap);
    }


}

package com.sdh.interviewvideotest;

import android.Manifest;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by sdh on 2016/10/20.
 */

public class BitmapCache implements ImageLoader.ImageCache {
    int max= (int) (Runtime.getRuntime().maxMemory()/8);
    LruCache<String,Bitmap> lruCache=new LruCache<>(max);
    @Override
    public Bitmap getBitmap(String s) {
        return lruCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        lruCache.put(s,bitmap);
    }
}

package com.zwonb.myvolley;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by zyb on 2017/8/22.
 */

public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mLruCache;

    public BitmapCache() {
        int maxSize = 8 * 1024 * 1024;
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {

        return mLruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mLruCache.put(url, bitmap);
    }
}

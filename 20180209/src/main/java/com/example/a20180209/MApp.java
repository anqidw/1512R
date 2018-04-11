package com.example.a20180209;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * author:Created by WangZhiQiang on 2018/2/14.
 */

public class MApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration =  new ImageLoaderConfiguration.Builder(this).build();

           //初始化
          ImageLoader.getInstance().init(configuration);
    }
}

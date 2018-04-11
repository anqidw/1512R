package com.example.a07;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * author:Created by WangZhiQiang on 2018/3/6.
 */

public class Mapp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        //ImageLoader全局配置
        File file=new File(Environment.getExternalStorageDirectory()+"/"+"image");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                //设置缓存目录
                .diskCache(new UnlimitedDiskCache(file))
                .diskCacheFileCount(100)
                .build();
        //初始化
        ImageLoader.getInstance().init(configuration);


    }
}

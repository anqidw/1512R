package com.example.myapplication;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.*;

import java.io.File;


/**
 * author:Created by WangZhiQiang on 2018/4/4.
 */

public class Myapp  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        File file=new File(Environment.getExternalStorageDirectory()+"/"+"image");
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this) .diskCache(new UnlimitedDiskCache(file))
                .threadPoolSize(3)
                .build();
        ImageLoader.getInstance().init(build);
    }
}

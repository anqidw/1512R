package com.example.anqiyuekao47;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * author:Created by WangZhiQiang on 2018/4/7.
 */

public class Myapp  extends Application {
    @Override

        public void onCreate() {
            super.onCreate();
            File file=new File(Environment.getExternalStorageDirectory()+"/"+"image");
            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                    .memoryCacheSize(100 * 1024 * 1024)//缓存最大值100M
                    .diskCache(new UnlimitedDiskCache(file))
                    .threadPoolSize(3)
                    .build();
            ImageLoader.getInstance().init(configuration);
        }

    }


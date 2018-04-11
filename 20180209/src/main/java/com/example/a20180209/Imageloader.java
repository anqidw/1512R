package com.example.a20180209;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * author:Created by WangZhiQiang on 2018/2/16.
 */

public class Imageloader  {


    public static DisplayImageOptions options(){
        DisplayImageOptions builder = new DisplayImageOptions.Builder()  //设置默认图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                //缓存到SD卡
                .cacheOnDisk(true)
                //缓存到内存
                .cacheInMemory(true)
                //图片的显示模式
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                //设置圆角图片
                .displayer(new RoundedBitmapDisplayer(20)).build();

          return  builder;
    }
}

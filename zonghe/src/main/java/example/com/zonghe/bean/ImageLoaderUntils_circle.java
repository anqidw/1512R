package example.com.zonghe.bean;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import example.com.zonghe.R;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class ImageLoaderUntils_circle {
    //设置图片参数
    public static DisplayImageOptions getDisplayOptions(){
        DisplayImageOptions build = new DisplayImageOptions.Builder()
                //设置默认图片
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
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

        return build;
    }

}

package com.example.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * author:Created by WangZhiQiang on 2018/4/4.
 */

public class Netstates  {

    //判断网络状态

    public static int getType(Context context) {
        int states = -1;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return states;
        }
        int type = info.getType();
        if (type == manager.TYPE_MOBILE) {
            return 0;
        }
        if (type == manager.TYPE_WIFI) {
            return 1;
        }
        return states;
    }

}

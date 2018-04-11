package example.com.zonghe.bean;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class NetState {
    public static int getNetType(Context context ){
        int noState =-1;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo==null){
            return noState;
        }
        int type = activeNetworkInfo.getType();
        if(type==ConnectivityManager.TYPE_WIFI){
            return 1;
        }else if(type==ConnectivityManager.TYPE_MOBILE){
            return 0;
        }

        return noState;
    }
}

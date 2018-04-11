package com.example.a07;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * author:Created by WangZhiQiang on 2018/3/6.
 */

public class Frag extends Fragment {
    public static Fragment getInstans(String title){
        Frag frag = new Frag();
        Bundle bundle = new Bundle();
        bundle.putString("bundle",title);
        return  frag;
    }
}

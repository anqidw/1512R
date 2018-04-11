package example.com.zonghe.ParentFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.zonghe.R;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class Fragment04 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment04_layout, container, false);
        return inflate;
    }
}

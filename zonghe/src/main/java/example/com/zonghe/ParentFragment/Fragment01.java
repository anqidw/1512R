package example.com.zonghe.ParentFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.com.zonghe.ChildFragment.ChildFragment01;
import example.com.zonghe.ChildFragment.ChildFragment02;
import example.com.zonghe.ChildFragment.ChildFragment03;
import example.com.zonghe.R;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class  Fragment01 extends Fragment{

    private ViewPager  viewpager01;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment01_layout, container, false);
        viewpager01 =(ViewPager) inflate.findViewById(R.id.viewpaget01);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //viewpager01获取数据
        final List<Fragment> list = new ArrayList<Fragment>();
        list.add(new ChildFragment01());
        list.add(new ChildFragment02());
        list.add(new ChildFragment03());
        //设置适配器
        viewpager01.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

    }
}

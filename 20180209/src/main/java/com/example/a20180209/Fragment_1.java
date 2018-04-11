package com.example.a20180209;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/2/9.
 */

public class Fragment_1 extends Fragment {

    private ViewPager viewPager;
    private List<Fragment> list=new ArrayList<Fragment>();
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragmeng_1,container,false);
            viewPager=view.findViewById(R.id.pager_09);

            return  view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list.add(new ChildFragment_1());
        list.add(new ChildFragment_2());
        list.add(new ChildFragment_3());

        viewPager.setAdapter(new Mpageadapter(getChildFragmentManager()));



    }

    private class Mpageadapter extends FragmentPagerAdapter {

        public Mpageadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}

package com.example.a03;

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

/**
 * author:Created by WangZhiQiang on 2018/2/25.
 */


public class Fragment1 extends Fragment{

   private List<Fragment> list=new ArrayList<Fragment>();
 private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment1,container,false);
        viewPager=view.findViewById(R.id.pager);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.add(new Child1());
        list.add(new Child2());
        list.add(new child3());
     viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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

package com.example.a07;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * author:Created by WangZhiQiang on 2018/3/6.
 */

public class Fragment2 extends Fragment {



    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private HorizontalScrollView horizontalScrollView;
 
    private String[] title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.list_2,container,false);

        linearLayout = view.findViewById(R.id.sce_1);
        horizontalScrollView = view.findViewById(R.id.scr);
         viewPager=view.findViewById(R.id.pager);
         title=new String[]{"头条","娱乐","头条","娱乐","头条","娱乐"};

        return  view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<TextView> list = new ArrayList<>();
        for (int i=0;i<title.length;i++){
                TextView textView=new TextView(getContext());
                textView.setText(title[i]);
                textView.setTextSize(20);
                if(i==0){
                    textView.setTextColor(Color.RED);
                }else{
                    textView.setTextColor(Color.BLACK);
                }

                textView.setOnClickListener((View.OnClickListener) getContext());
                textView.setId(i+1000);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.addView(textView,params);
            list.add(textView);
        }


    }
}

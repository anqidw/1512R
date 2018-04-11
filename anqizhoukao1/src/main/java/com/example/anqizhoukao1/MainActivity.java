package com.example.anqizhoukao1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private RadioGroup group;
    private RadioButton b1;
    private RadioButton b2;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件ID
        viewPager=findViewById(R.id.pager);
        group = findViewById(R.id.group);
        b1 = findViewById(R.id.bu1);
        b2 = findViewById(R.id.bu2);
        list = new ArrayList<Fragment>();
        list.add(new Fragment1());
        list.add(new Fragment2());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch(i){
                            case R.id.bu1:
                          viewPager.setCurrentItem(0);
                            break;
                        case R.id.bu2:
                            viewPager.setCurrentItem(1);
                            break;
                        }
            }
        });
    }
}

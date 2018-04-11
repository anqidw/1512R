package com.example.anqiyekao_1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class   MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout;
    private String[] title;
    private ViewPager viewPager;
    private HorizontalScrollView horizontalScrollView;
    private ArrayList<TextView> textViewa;
    private List<Fragment> list=new ArrayList<Fragment>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=findViewById(R.id.lin);
        horizontalScrollView=findViewById(R.id.cro);
        viewPager=findViewById(R.id.pager);
        title=new String[]{"头条","头条","头条","头条","头条","头条","头条","头条","头条"};


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag,new Fragment1()).commit();

        list.add(new Fragment1());
        textViewa = new ArrayList<TextView>();

         for (int i=0;i<title.length;i++)

        {



            TextView textView = new TextView(this);
            textView.setText(title[i]);
            textView.setTextSize(20);
            if(i==0){
                 textView.setTextColor(Color.RED);

            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

             layoutParams.setMargins(10,10,10,10);
             textView.setId(i);
             textView.setOnClickListener(this);
            textViewa.add(textView);
            linearLayout.addView(textView,layoutParams);
        }
       
        
           
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<title.length;i++){
                      if(i==position){
                           textViewa.get(i).setTextColor(Color.RED);

                      }else{
                          textViewa.get(i).setTextColor(Color.BLACK);

                      }
                }
                 int width=textViewa.get(0).getWidth();
                horizontalScrollView.scrollTo((width+20)*position,0);
                 list.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

         viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
             @Override
             public Fragment getItem(int position) {
                 return  list.get(position);
             }

             @Override
             public int getCount() {
                 return list.size();
             }
         });

    }

    @Override
    public void onClick(View view) {
         int id=view.getId();
         viewPager.setCurrentItem(id);

    }
}

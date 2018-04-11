package com.example.a20180212;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> list=new ArrayList<Fragment>();
    private RadioGroup group;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group = findViewById(R.id.group_12);
        list.add(new Fragment_1());
        list.add(new Fragment_2());
        list.add(new Fragment_3());
        list.add(new Fragment_4());
        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add(R.id.fragment_12,list.get(0)).commit();



        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            switch(i){
                                    case R.id.button_1:
                                     supportFragmentManager.beginTransaction().replace(R.id.fragment_12,list.get(0)).commit();
                                    break;
                                case R.id.button_2:
                                    supportFragmentManager.beginTransaction().replace(R.id.fragment_12,list.get(1)).commit();
                                    break;

                                case R.id.button_3:
                                    supportFragmentManager.beginTransaction().replace(R.id.fragment_12,list.get(2)).commit();
                                    break;
                                    case R.id.button_4:
                                    supportFragmentManager.beginTransaction().replace(R.id.fragment_12,list.get(3)).commit();
                                    break;
                                }
            }
        });

    }
}

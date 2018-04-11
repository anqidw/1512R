package com.example.a20180209;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Fragment> list=new ArrayList<Fragment>();
    private RadioGroup group;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //获取控件ID
        group = findViewById(R.id.group);

        list.add(new Fragment_1());
        list.add(new Fragment_2());
        list.add(new Fragment_3());
        list.add(new Fragment_4());
        supportFragmentManager = getSupportFragmentManager();

        supportFragmentManager.beginTransaction().add(R.id.frament,list.get(0)).commit();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch(i){
                            case R.id.bu_09:
                             supportFragmentManager.beginTransaction().replace(R.id.frament,list.get(0)).commit();
                            break;

                        case R.id.bu1_09:
                            supportFragmentManager.beginTransaction().replace(R.id.frament,list.get(1)).commit();
                            break;

                        case R.id.bu2_09:
                            supportFragmentManager.beginTransaction().replace(R.id.frament,list.get(2)).commit();
                            break;

                        case R.id.bu3_09:
                            supportFragmentManager.beginTransaction().replace(R.id.frament,list.get(3)).commit();
                            break;



                        }
            }
        });

    }
}

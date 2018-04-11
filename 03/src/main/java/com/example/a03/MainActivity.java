package com.example.a03;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {


    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group=findViewById(R.id.group);

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout,new Fragment1()).commit();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch(i){
                            case R.id.bu1:
                                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                               fragmentTransaction1.replace(R.id.layout,new Fragment1()).commit();
                                break;
                        case R.id.bu2:
                            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction2.replace(R.id.layout,new Fragment2()).commit();
                            break;
                        case R.id.bu3:
                            FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction3.replace(R.id.layout,new Fragment3()).commit();
                            break;
                        case R.id.bu4:
                            FragmentTransaction fragmentTransaction4= getSupportFragmentManager().beginTransaction();
                            fragmentTransaction4.replace(R.id.layout,new Fragment4()).commit();
                            break;
                        }
            }
        });
    }

}

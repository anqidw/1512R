package com.example.a07;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {



    private RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        group=findViewById(R.id.group);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
         fragmentTransaction.replace(R.id.lay,new Fragment1()).commit();

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                     switch(i){
                         case R.id.bu1:
                             FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                             fragmentTransaction.replace(R.id.lay,new Fragment1()).commit();
                             break;
                             case R.id.bu2:
                                 FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                                 fragmentTransaction2.replace(R.id.lay,new Fragment2()).commit();

                             break;

                         }
            }
        });

    }
}

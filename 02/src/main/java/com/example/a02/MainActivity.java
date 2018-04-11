package com.example.a02;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {


    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        group = findViewById(R.id.group25);
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment25, new Fragment1()).commit();

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup radioGroup, int i) {
                   switch(i){
                       case R.id.bu1:
                               FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                               fragmentTransaction1.replace(R.id.fragment25,new Fragment1()).commit();
                               break;
                       case R.id.bu2:
                           FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                           fragmentTransaction2.replace(R.id.fragment25,new Fragment2()).commit();
                           break;
                       case R.id.bu3:
                           FragmentTransaction fragmentTransaction3= getSupportFragmentManager().beginTransaction();
                           fragmentTransaction3.replace(R.id.fragment25,new Fragment3()).commit();
                           break;

                       }
          }
      });
    }


}

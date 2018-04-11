package example.com.zonghe;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import example.com.zonghe.ParentFragment.Fragment01;
import example.com.zonghe.ParentFragment.Fragment02;
import example.com.zonghe.ParentFragment.Fragment03;
import example.com.zonghe.ParentFragment.Fragment04;

public class MainActivity extends AppCompatActivity {


    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        group = (RadioGroup) findViewById(R.id.group);
        //默认加载
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frame, new Fragment01()).commit();
        //group监听
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.one:
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame, new Fragment01()).commit();
                        break;
                    case R.id.two:
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, new Fragment02()).commit();
                        break;
                    case R.id.three:
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, new Fragment03()).commit();
                        break;
                    case R.id.four:
                        FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, new Fragment04()).commit();
                        break;


                }
            }
        });


    }
}

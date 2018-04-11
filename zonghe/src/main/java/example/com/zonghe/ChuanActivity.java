package example.com.zonghe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import example.com.zonghe.ChildFragment.MyAdapter;
import example.com.zonghe.bean.UserData;

public class ChuanActivity extends AppCompatActivity {

    private ListView listview04;
    List<UserData.DataBean> listAll = new ArrayList<UserData.DataBean>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuan);
        listview04=(ListView)findViewById(R.id.listview04);
          //设置适配器
        myAdapter = new MyAdapter(listAll, ChuanActivity.this);
        listview04.setAdapter(myAdapter);
        //接收值
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        List<UserData.DataBean> list = (List<UserData.DataBean>) bundle.getSerializable("list");
        Log.i("顶顶顶顶",list.size()+"");
        listAll.addAll(list);
        myAdapter.notifyDataSetChanged();
    }

}

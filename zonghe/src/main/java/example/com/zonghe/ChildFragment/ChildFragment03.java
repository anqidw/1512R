package example.com.zonghe.ChildFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import example.com.zonghe.R;
import example.com.zonghe.bean.NetHttpurlConnection;
import example.com.zonghe.bean.UserData;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class ChildFragment03 extends Fragment {

    private ListView listview03;
    List<UserData.DataBean> listAll = new ArrayList<UserData.DataBean>();
    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //刷新适配器
            adapter.notifyDataSetChanged();
        }
    };
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.childfragment03_layout, container, false);
        listview03 = (ListView) view.findViewById(R.id.listview03);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            //设置适配器
        adapter = new MyAdapter(listAll, getActivity());
        listview03.setAdapter(adapter);
        //获取数据
        new Thread(){
            @Override
            public void run() {
                super.run();
                //获取数据
                String text = NetHttpurlConnection.getText(urlString);
                Gson gson = new Gson();
                UserData userData = gson.fromJson(text, UserData.class);
                List<UserData.DataBean> data = userData.getData();
                listAll.addAll(data);
                handler.sendEmptyMessage(1);
            }
        }.start();

    }
}

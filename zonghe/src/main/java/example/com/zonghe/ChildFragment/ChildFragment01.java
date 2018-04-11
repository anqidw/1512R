package example.com.zonghe.ChildFragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import example.com.zonghe.ChuanActivity;
import example.com.zonghe.R;
import example.com.zonghe.bean.NetHttpurlConnection;
import example.com.zonghe.bean.UserData;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class ChildFragment01 extends Fragment{

    private ListView listview01;
    List<UserData.DataBean> list=new ArrayList<UserData.DataBean>();
    public String urlString="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.childfragment01_layout, container, false);
        listview01 =(ListView)view.findViewById(R.id.listview01);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置适配器
        myAdapter = new MyAdapter(list,getActivity());
        listview01.setAdapter(myAdapter);
        //获取数据
        new MyAsyncTask().execute(urlString);
        //点击条目 将值传给childfragment03
        listview01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), ChuanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",(Serializable) list);
                intent.putExtra("bundle",bundle);

                startActivity(intent);
            }
        });

    }

    private class MyAsyncTask extends AsyncTask<String ,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            String text = NetHttpurlConnection.getText(strings[0]);
            return text;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            UserData userData = gson.fromJson(s, UserData.class);
            List<UserData.DataBean> data = userData.getData();
            list.addAll(data);
            myAdapter.notifyDataSetChanged();
        }
    }
}

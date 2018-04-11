package example.com.zonghe.ChildFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import example.com.zonghe.R;
import example.com.zonghe.bean.NetHttpClient;
import example.com.zonghe.bean.UserData;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class ChildFragment02 extends Fragment {

    private ListView listview02;
    List<UserData.DataBean> list = new ArrayList<UserData.DataBean>();
    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.childfragment02_layout, container, false);
        listview02 = (ListView) view.findViewById(R.id.listview02);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置适配器
        adapter = new MyAdapter(list, getActivity());
        listview02.setAdapter(adapter);
        //获取数据
        new MyAsyncTask().execute(urlString);
    }

    private class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String httpClient = NetHttpClient.getHttpClient(strings[0]);
            return httpClient;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            UserData userData = gson.fromJson(s, UserData.class);
            List<UserData.DataBean> data = userData.getData();
            list.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }
}

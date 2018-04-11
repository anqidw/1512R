package com.example.a20180209;

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

/**
 * author:Created by WangZhiQiang on 2018/2/9.
 */

public class ChildFragment_1 extends Fragment {
    private ListView listView;
    public String urlString="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";

    private List<Bean_.databean> list=new ArrayList<Bean_.databean>();

    Handler handler = new Handler(){
    //xxxxxxxx

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                myadapter.notifyDataSetChanged();
            }
        }
    };
    private Myadapter myadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.list_09,container,false);
        listView = view.findViewById(R.id.list_9);

        new Thread(){

            public void run(){
                String netjson = Bean.getNetjson(urlString);
                Gson gson = new Gson();
                Bean_ bean_ = gson.fromJson(netjson, Bean_.class);
                List<Bean_.databean> data = bean_.getData();
                list.addAll(data);
                handler.sendEmptyMessage(1);
            }

        }.start();


       return  view;
     }

     @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            myadapter = new Myadapter(list,getActivity());
            listView.setAdapter(myadapter);

    }
}

package com.example.anqizhoukao1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
public class Fragment1 extends Fragment{
    private String  Stringurli="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private List<Bean.Databean> list = new ArrayList<Bean.Databean>();
    private Listadapter myadapter;
    private Handler handler = new Handler(){
    //xxxxxxxx
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

      myadapter.notifyDataSetChanged();

        }
    };
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,container,false);
        listView = view.findViewById(R.id.listview);

        myadapter = new Listadapter();
        listView.setAdapter(myadapter);

        new Thread(){

            public void  run(){
                String json = Utils.getNetConnection(Stringurli);
                Gson gson = new Gson();
                Bean bean=gson.fromJson(json,Bean.class);
                List<Bean.Databean> data = bean.getData();
                list.addAll(data);
                handler.sendEmptyMessage(1);

            }

        }.start();
        return view;
        //获取控件 ID

            }

       private  class Listadapter extends BaseAdapter{

           @Override
           public int getCount() {
               return list.size();
           }

           @Override
           public Object getItem(int i) {

               return list.get(i);
           }

           @Override
           public long getItemId(int i) {

               return i;
           }

           @Override
           public View getView(int i, View view, ViewGroup viewGroup) {
              ViewHolder holder;
              if(view==null){
                 view=view.inflate(getActivity(),R.layout.image,null);
                 holder = new ViewHolder();
                 holder.textView=(TextView)view.findViewById(R.id.text1);
                 view.setTag(holder);
              }else {

                  holder = (ViewHolder) view.getTag();

              }
              holder.textView.setText(list.get(i).getNews_title());


               return view;
           }

           class ViewHolder{
            private TextView textView;
           }

    }
}

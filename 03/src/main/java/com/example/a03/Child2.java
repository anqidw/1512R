package com.example.a03;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/2/25.
 */

public class Child2 extends Fragment {

    public String urlString="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private List<Bean.DataBean> list=new ArrayList<Bean.DataBean>();
    private ListView listView;
    private Myada myada;
    Handler handler = new Handler(){
        //xxxxxxxx

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){

                myada.notifyDataSetChanged();

            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.child2,container,false);
     listView=view.findViewById(R.id.list_2);

        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myada = new Myada();
        listView.setAdapter(myada);


        new Thread(){

            public void run(){
                Gson gson = new Gson();
                String s = Utils.GetJson(urlString);

                Bean bean=gson.fromJson(s,Bean.class);
                List<Bean.DataBean> data=bean.getData();
                list.addAll(data);
                handler.sendEmptyMessage(1);

            }

        }.start();

    }

    private class Myada extends BaseAdapter{
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
              view=view.inflate(getActivity(),R.layout.list_3,null);
              holder=new ViewHolder();
              holder.imageView=view.findViewById(R.id.image_3);
              holder.textView=view.findViewById(R.id.text_3);
              view.setTag(holder);
           }else{

              holder= (ViewHolder) view.getTag();
           }
              new MYas(holder.imageView).execute(list.get(i).getPic_url());
               holder.textView.setText(list.get(i).getNews_title());
            return view;
        }

        class ViewHolder{
            ImageView imageView;
            TextView  textView;
        }

    }

    private  class MYas  extends AsyncTask<String,Void ,Bitmap>{

        private ImageView img;
        public  MYas (ImageView imageView){
            this.img=imageView;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap getbitmap = Utils.getbitmap(strings[0]);
            return getbitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
        }
    }

    }



package com.example.anqiyuekao;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private ListView listView;
   private List<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
   public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";

    Handler handler = new Handler(){
        //xxxxxxxx

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myadapter.notifyDataSetChanged();
        }
    };
    private Myadapter myadapter;
    private ImageLoader instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=findViewById(R.id.list_1);

        instance = ImageLoader.getInstance();

        myadapter = new Myadapter();
        listView.setAdapter(myadapter);

        new Thread(){

            public void run(){
                String json = Util.getJson(urlString);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(json, Bean.class);
                List<Bean.DataBean> data = bean.getData();
                list.addAll(data);
                handler.sendEmptyMessage(1);
            }

        }.start();


    }

    private class Myadapter  extends BaseAdapter{

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return position%2;
        }

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
            int s = getItemViewType(i);
                switch(s){
                        case 0:
                          ViewHolder holder;
                          if(view==null){
                              view=View.inflate(getApplicationContext(),R.layout.list_1,null);
                              holder =new ViewHolder();
                              holder.imageView=view.findViewById(R.id.image);
                              holder.textView=view.findViewById(R.id.text_1);
                              view.setTag(holder);
                          }else{
                              holder= (ViewHolder) view.getTag();
                          }
                          holder.textView.setText(list.get(i).getNews_title());
                          instance.displayImage(list.get(i).getPic_url(),holder.imageView);


                        break;
                        case 1:
                            ViewHolder2 holder2;
                            if(view==null){
                                view=View.inflate(getApplicationContext(),R.layout.list_2,null);
                                holder2 =new ViewHolder2();

                                holder2.textView2=view.findViewById(R.id.text_2);
                                view.setTag(holder2);
                            }else{
                                holder2= (ViewHolder2) view.getTag();
                            }
                            holder2.textView2.setText(list.get(i).getNews_title());


                        break;

                    }
            return view;
        }

        class ViewHolder{
            TextView textView;
            ImageView imageView;
        }
        class ViewHolder2{
            TextView textView2;
        }





    }
}

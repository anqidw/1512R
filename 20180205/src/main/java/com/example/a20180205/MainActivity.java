package com.example.a20180205;

import android.graphics.Bitmap;
import android.os.AsyncTask;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    String  urlString="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";

    List<Bean.databean> list = new ArrayList<Bean.databean>();

    private Myadapter myadapter;
     Handler handler = new Handler(){
    //xxxxxxxx
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
              myadapter.notifyDataSetChanged();
         }
     };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_05);
        myadapter = new Myadapter();
        new Thread(){
            public void run(){
                String json = utils.getNetjson(urlString);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(json, Bean.class);
                List<Bean.databean> data = bean.getData();
                list.addAll(data);
                handler.sendEmptyMessage(1);
            }
        }.start();
        listView.setAdapter(myadapter);

    }
    public class  Myadapter  extends BaseAdapter{
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
             view=view.inflate(getApplicationContext(),R.layout.image,null);
             holder=new ViewHolder();
             holder.textView=view.findViewById(R.id.text_06);
             holder.imageView=view.findViewById(R.id.image_06);
             view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
            holder.textView.setText(list.get(i).getNews_title());
            new MyAsyncTask(holder.imageView).execute(list.get(i).getPic_url());
            return view;
        }
         class ViewHolder{
            private TextView textView;
            private  ImageView imageView;
        }
    }

    private class MyAsyncTask extends AsyncTask<String,Void ,Bitmap>{

         private ImageView imageView;
        public MyAsyncTask(ImageView imageView) {
            this.imageView=imageView;
        }


        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap=utils.getNetbitmap(strings[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
           imageView.setImageBitmap(bitmap);
        }
    }



}

package com.example.myapplication;

import android.annotation.SuppressLint;
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

import com.example.myapplication.sql.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String urlString="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";

    private PullToRefreshListView   plv;
     private List<Bean.DataBean> list=new ArrayList<Bean.DataBean>();
     private int page=0;

    private Myadapter myadapter;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==1){
                String sss = (String) msg.obj;
                dao.insertData(urlString+page,sss);
                Gson gson = new GsonBuilder().create();
                Bean bean = gson.fromJson(sss, Bean.class);
                List<Bean.DataBean> data = bean.getData();
                list.addAll(data);
                myadapter.notifyDataSetChanged();
                plv.onRefreshComplete();
            }
        }
    };
    private ImageLoader instance;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取id
        plv=(PullToRefreshListView) findViewById(R.id.piv);
        //上下拉都支持
        plv.setMode(PullToRefreshListView.Mode.BOTH);

        myadapter = new MainActivity.Myadapter();
        plv.setAdapter(myadapter);

        //获取数据
        getdata(0);

        instance = ImageLoader.getInstance();


        dao = new UserDao(MainActivity.this);


        //设置上拉刷新下拉加载
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
               list.clear();
               getdata(0);


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                     page++;
                     getdata(page);
            }
        });

    }


    //请求数据
    public void getdata(int page) {
                 //数据接口
        final String s = urlString + page;
       //判断网络
        if(Netstates.getType(MainActivity.this) !=-1){
                    new Thread(){
                            //当联网状态下就是解析数据 并且将数据存入数据库中
                            public void run(){
                                String getnetjson = NetUtils.getnetjson(s);
                                Message msg = new Message();
                               msg.what=1;
                               msg.obj=getnetjson;
                               handler.sendMessage(msg);

                            }

                    }.start();
        }else{
            //查询
            String s1 = dao.queryData(s);
                 if(!s.isEmpty()){
                     Gson gson = new GsonBuilder().create();
                     Bean bean = gson.fromJson(s1, Bean.class);
                     List<Bean.DataBean> data = bean.getData();
                     list.addAll(data);
                     myadapter.notifyDataSetChanged();
                     plv.onRefreshComplete();
                 }
        }
    }

    private class Myadapter extends BaseAdapter {
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
             if(view ==null){
                  view=View.inflate(MainActivity.this,R.layout.image,null);
                  holder=new ViewHolder();
                 holder.name=view.findViewById(R.id.name);
                 holder.img=view.findViewById(R.id.image_);
                 view.setTag(holder);
             }else{
                 holder= (ViewHolder) view.getTag();
             }
             holder.name.setText(list.get(i).getNews_title());

            instance.displayImage(list.get(i).getPic_url(),holder.img);
            return view;
        }
  }

   class ViewHolder{

        private TextView name;
        private ImageView img;

   }





}

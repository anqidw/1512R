package com.example.anqiyuekao47;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView ptr;
    List<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    int page;
    private ImageLoader instance;
    static String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                //插入数据
                String ss = (String) msg.obj;
                dao.insertData(urlString + page, ss);
                Gson gson = new GsonBuilder().create();
                Bean dataBeans = gson.fromJson(ss, Bean.class);
                List<Bean.DataBean> data = dataBeans.getData();
                list.addAll(data);
                //刷新适配器
                adapter.notifyDataSetChanged();
                //UI同步
                ptr.onRefreshComplete();
            }
        }
    };

    private MyAdapter adapter;
    private Dao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        ptr = (PullToRefreshListView) findViewById(R.id.ptr);
        //设置模式
        ptr.setMode(PullToRefreshBase.Mode.BOTH);
        dao = new Dao(MainActivity.this);
        //设置适配器
        adapter = new MyAdapter();
        ptr.setAdapter(adapter);
        //获取数据
        getDatas(0);

        //ImageLoader实体类
        instance = ImageLoader.getInstance();
        //刷新监听
        ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                list.clear();
                getDatas(0);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page++;
                getDatas(page);
            }
        });

    }

    //数据....
    private void getDatas(int page) {
        //数据接口
        final String umrl = urlString + page;
        //判断网络
        if (status.getType(MainActivity.this) != -1) {

            Toast.makeText(MainActivity.this, "有网啦!", Toast.LENGTH_SHORT).show();
                //子线程
            new Thread() {
                    @Override
                public void run() {
                    super.run();
                    // 请求数据
                    String httpUrl = Utils.getnetjson(umrl);
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = httpUrl;
                    handler.sendMessage(msg);
                }
            }.start();
            // new MyAsyncTask().execute(umrl);
        } else {
            Toast.makeText(MainActivity.this, "无网络,请重试!", Toast.LENGTH_SHORT).show();
            //查询
            String s = dao.queryData(umrl);
            if (!s.isEmpty()) {
                Gson gson = new GsonBuilder().create();
                Bean dataBeans = gson.fromJson(s, Bean.class);
                List<Bean.DataBean> data = dataBeans.getData();
                list.addAll(data);
                adapter.notifyDataSetChanged();
                ptr.onRefreshComplete();
            }
        }
    }

    //适配器...
    private class MyAdapter extends BaseAdapter {
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
            if (view == null) {
                view = View.inflate(MainActivity.this, R.layout.style, null);
                holder = new ViewHolder();
                holder.name = view.findViewById(R.id.text_);
                holder.img = view.findViewById(R.id.image_);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            //赋值
            holder.name.setText(list.get(i).getNews_id());

            instance.displayImage(list.get(i).getPic_url(), holder.img );
            return view;
        }
    }

    class ViewHolder {
        private TextView name;
        private ImageView img;
    }
}

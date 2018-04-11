package com.example.a20180209;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.xlistviewlibrary.View.XListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/2/9.
 */

public class Fragment_2 extends Fragment implements XListView.IXListViewListener{
    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";
    private List<Bean_.databean> list=new ArrayList<Bean_.databean>();
    int page;
    private XListView xListView;
    private Myadapter myadapter;
    private ImageLoader instance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.xlist,container,false);

        xListView=view.findViewById(R.id.xlist);
        return  view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myadapter = new Myadapter();
        xListView.setAdapter(myadapter);

        instance = ImageLoader.getInstance();
        getNetData(0);
        //允许你下拉刷新
        xListView.setPullLoadEnable(true);
        //xlistview设置监听
        xListView.setXListViewListener(this);

    }
    private void getNetData(int page) {
          new aaa().execute(urlString+page);

    }
    @Override
    public void onRefresh() {
        //先清空集合
        list.clear();
        //请求数据
        getNetData(0);

    }

    @Override
    public void onLoadMore() {
        //加载一次添加一次
        page++;
        getNetData(page);
    }

    private class Myadapter extends BaseAdapter{

        //返回的条目数量
        @Override
        public int getViewTypeCount() {
            return 2;
        }

        //返回的条目类型

        @Override
        public int getItemViewType(int position) {

            return position % 2;
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
            int viewType = getItemViewType(i);
            switch (viewType) {
                case 0:
                    ViewHolder01 holder01;
                    if (view == null) {
                        view = View.inflate(getActivity(), R.layout.list_layout, null);
                        holder01 = new ViewHolder01();
                        holder01.name = view.findViewById(R.id.name);
                        holder01.img = view.findViewById(R.id.img);
                        view.setTag(holder01);
                    } else {
                        holder01 = (ViewHolder01) view.getTag();
                    }
                    //赋值
                    holder01.name.setText(list.get(i).getNews_title());
                    //获取图片
                    DisplayImageOptions options = Imageloader.options();
                    //展示图片
                    instance.displayImage(list.get(i).getPic_url(), holder01.img, options);

                    break;
                case 1:
                    ViewHolder02 holder02;
                    if (view == null) {
                        view = View.inflate(getActivity(), R.layout.list02_layout, null);
                        holder02 = new ViewHolder02();
                        holder02.name02 = view.findViewById(R.id.name2);
                        view.setTag(holder02);
                    } else {
                        holder02 = (ViewHolder02) view.getTag();
                    }
                    //赋值
                    holder02.name02.setText(list.get(i).getNews_summary());
                    break;

            }
            return view;
        }

        class ViewHolder01 {
            private TextView name;
            private ImageView img;
        }
        class ViewHolder02 {
            private TextView name02;
        }



    }



    public  class aaa extends AsyncTask<String,Void,String> {
        public aaa() {
            super();
        }

        @Override
        protected String doInBackground(String... strings) {
            String httpClient = Bean.getHttpClient(strings[0]);

            return httpClient;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Bean_ bean_ = gson.fromJson(s, Bean_.class);
            List<Bean_.databean> data = bean_.getData();
            list.addAll(data);
            myadapter.notifyDataSetChanged();
            uiComplete();
        }
    }

    private void uiComplete() {
        //停止上啦下拉
        xListView.stopLoadMore();
        xListView.stopRefresh();
        //刷新的时间
        xListView.setRefreshTime(""+System.currentTimeMillis());
    }

}

package com.example.a03;

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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/2/25.
 */

public class Fragment2 extends Fragment implements XListView.IXListViewListener {

    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";
    private List<Bean.DataBean> list=new ArrayList<Bean.DataBean>();
    private XListView xListView;
    int page;
    private ImageLoader instance;
    private Myadapter myadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment2,container,false);
       xListView=view.findViewById(R.id.xlist_1);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        instance = ImageLoader.getInstance();
        myadapter = new Myadapter();
        xListView.setAdapter(myadapter);

        getdata(0);

   //设置容许下拉刷新
    xListView.setPullLoadEnable(true);
    //设置监听
    xListView.setXListViewListener(this);

    }

    private void getdata(int page){
       new MYAS().execute(urlString+page);
    }

    @Override
    public void onRefresh() {//下拉刷新

         list.clear();//清空集合
          getdata(0);//请求数据
    }

    @Override
    public void onLoadMore() {//上拉加载
        page++;
        getdata(page);

    }

    private class Myadapter extends BaseAdapter {

        //返回条目的数量
        @Override
        public int getViewTypeCount() {
          return  2;
        }

        //返回条目的类型


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
            int type = getItemViewType(i);//得到条目的类型
                switch(type){
                        case 0:
                            ViewHolder1 holder1;
                            if(view==null){
                          view=view.inflate(getActivity(),R.layout.list_4,null);
                          holder1 =new ViewHolder1();
                          holder1.imageView=view.findViewById(R.id.image_4);
                          holder1.textView=view.findViewById(R.id.text_4);
                          view.setTag(holder1);
                            }else{
                                holder1= (ViewHolder1) view.getTag();
                            }
                            //赋值
                            holder1.textView.setText(list.get(i).getNews_title());
                            //获取图片
                            DisplayImageOptions displayOptions = ImageUtils.getDisplayOptions();
                              instance.displayImage(list.get(i).getPic_url(),holder1.imageView,displayOptions);
                            break;
                        case 1:

                            ViewHolder2 holder2;
                            if (view == null) {
                                view = View.inflate(getActivity(), R.layout.list_2, null);
                                holder2 = new ViewHolder2();
                                holder2.textView2 = view.findViewById(R.id.name_1);
                                view.setTag(holder2);
                            } else {
                                holder2 = (ViewHolder2) view.getTag();
                            }
                            //赋值
                            holder2.textView2.setText(list.get(i).getNews_summary());

                        break;

                    }
            return view;
        }

        class ViewHolder1{
            TextView textView;
            ImageView imageView;
        }
        class ViewHolder2{
            TextView textView2;

        }

    }

    private class MYAS extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            String s = Utils.GetJson(strings[0]);
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            list.addAll(data);
            myadapter.notifyDataSetChanged();
            xListView.stopLoadMore();
            xListView.stopRefresh();
            //刷新的时间
            xListView.setRefreshTime(""+System.currentTimeMillis());
        }
    }
}

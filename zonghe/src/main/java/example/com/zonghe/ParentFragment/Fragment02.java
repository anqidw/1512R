package example.com.zonghe.ParentFragment;

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

import example.com.zonghe.R;
import example.com.zonghe.bean.ImageLoaderUntils_circle;
import example.com.zonghe.bean.NetHttpurlConnection;
import example.com.zonghe.bean.UserData;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class Fragment02 extends Fragment implements XListView.IXListViewListener {


    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";
   int page;
    List<UserData.DataBean> list = new ArrayList<UserData.DataBean>();
    private MyAdapter adapter;
    private ImageLoader instance;

    private XListView xListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment02_layout, container, false);
        xListView = (XListView)inflate.findViewById(R.id.xlistview);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置适配器
        adapter = new MyAdapter();
        xListView.setAdapter(adapter);
        //得到图片实例
        instance = ImageLoader.getInstance();
       //请求网络的方法
        getNetData(0);

        //允许你下拉刷新
        xListView.setPullLoadEnable(true);
        //xlistview设置监听
        xListView.setXListViewListener(this);

    }

    private void getNetData(int page) {
        new MyAsyncTask().execute(urlString+page);
    }

    //监听重写的方法
    @Override
    public void onRefresh() {//下拉刷新
        //先清空集合
        list.clear();
        //请求数据
        getNetData(0);
    }

    @Override
    public void onLoadMore() {//上啦加载
        //加载一次添加一次
        page++;
        getNetData(page);
    }

    private class MyAdapter extends BaseAdapter {

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
            //得到条目的类型
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
                    DisplayImageOptions options = ImageLoaderUntils_circle.getDisplayOptions();
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

    private class MyAsyncTask extends AsyncTask<String ,Void ,String >{
        @Override
        protected String doInBackground(String... strings) {
            return NetHttpurlConnection.getText(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //解析
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            UserData userData = gson.fromJson(s, UserData.class);
            List<UserData.DataBean> data = userData.getData();
            list.addAll(data);
            //刷新适配器
            adapter.notifyDataSetChanged();
            //上啦加载,下拉刷新同步
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

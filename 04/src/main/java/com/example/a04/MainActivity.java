package com.example.a04;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.xlistviewlibrary.View.XListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {


    private int page;
    public String urlString="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
   private List<Bean.DataBean> list=new ArrayList<Bean.DataBean>();
   private XListView xListView;
    private Myadapter myadapter;
    private ImageLoader instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件ID
         xListView=findViewById(R.id.xlist_28);
        myadapter = new Myadapter();
        xListView.setAdapter(myadapter);


        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);


        instance = ImageLoader.getInstance();

        getdata(0);

       

    }
    private void getdata(int page){
          new ASY().execute(urlString+page);
    }

    @Override
    public void onRefresh() {
              list.getClass();
              getdata(0);
    }

    @Override
    public void onLoadMore() {
    page++;
          getdata(page);
    }

    private class  Myadapter extends BaseAdapter{

         //返回条目数
        @Override
        public int getViewTypeCount() {
            return  2;
        }

        @Override
        public int getItemViewType(int position) {
            return  position%2;
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
        public View getView(int i, View view, ViewGroup viewGroup){
            int type = getItemViewType(i);
                 switch(type){
                         case 0:
                      ViewHolder1 holder1;
                      if(view==null){
                             view=view.inflate(getApplication(),R.layout.xlist,null);
                           holder1=new ViewHolder1();
                           holder1.imageView=view.findViewById(R.id.image_1);
                           holder1.textView=view.findViewById(R.id.text_1);
                           view.setTag(holder1);
                      }else{

                          holder1= (ViewHolder1) view.getTag();

                      }

                              instance.displayImage(list.get(i).getPic_url(),holder1.imageView);
                              holder1.textView.setText(list.get(i).getNews_title());
                             break;
                         case 1:
                             ViewHolder2 holder2;
                             if(view==null){
                                 view=view.inflate(getApplication(),R.layout.xlist_1,null);
                                 holder2=new ViewHolder2();
                                 holder2.textView2=view.findViewById(R.id.text_2);
                                 view.setTag(holder2);
                             }else{
                                 holder2= (ViewHolder2) view.getTag();
                             }

                             holder2.textView2.setText(list.get(i).getNews_summary());
                             break;
                     }
            return view;
        }
        class ViewHolder1{
            ImageView imageView;
            TextView textView;
        }
        class ViewHolder2{

            TextView textView2;
        }

    }


    private class ASY  extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
   
            String s = Utils.GetJson(strings[0]);

            return  s;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            list.addAll(data);
           myadapter.notifyDataSetChanged();
            //停止上啦下拉
            xListView.stopLoadMore();
            xListView.stopRefresh();
            //刷新的时间
            xListView.setRefreshTime(""+System.currentTimeMillis());
        }
    }

}

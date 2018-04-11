package com.example.a01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    public String urlString="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";


   private   List<Bean.Databean> list=new ArrayList<Bean.Databean>();
    private ListView listView;
    private ImageLoader instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list23);

        Myadapter myadapter = new Myadapter();

        //得到图片的实例
        instance = ImageLoader.getInstance();

        listView.setAdapter(myadapter);
        
        



    }

    private class  Myadapter extends BaseAdapter{
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

               ViewHolder viewHolder;
               if(view==null){
                  view=view.inflate(getApplicationContext(),R.layout.aaa,null);
                   viewHolder=new ViewHolder();
                   viewHolder.imageView=view.findViewById(R.id.image23);
                   view.setTag(viewHolder);

               }else{
                   viewHolder= (ViewHolder)view.getTag();
               }

            DisplayImageOptions displayOptions = Image.getDisplayOptions();
             instance.displayImage(list.get(i).getPic_url(),viewHolder.imageView,displayOptions);
            return view;
        }

        class ViewHolder{

            private ImageView imageView;

        }

    }
}

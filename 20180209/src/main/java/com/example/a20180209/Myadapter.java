package com.example.a20180209;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/2/11.
 */

class Myadapter extends BaseAdapter{

    private List<Bean_.databean> list;
    private  Context context;
    public Myadapter(List<Bean_.databean> list, Context context) {
      this.list=list;
      this.context=context;
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
        ViewHolder holder;
        if(view==null){
           view=View.inflate(context,R.layout.image,null);
           holder=new ViewHolder();
           holder.textView=view.findViewById(R.id.text_09);
           holder.imageView=view.findViewById(R.id.image_09);
           view.setTag(holder);
        }else {
           holder= (ViewHolder) view.getTag();
        }
           holder.textView.setText(list.get(i).getNews_title());
        new MyAs(holder.imageView).execute(list.get(i).getPic_url());
        return view;
    }
    private class ViewHolder{
        TextView textView;
        ImageView imageView;
    }


    private class MyAs  extends AsyncTask<String,Void ,Bitmap>{

       private ImageView imageView;
        public MyAs(ImageView imageView) {
            super();
            this.imageView=imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = Bean.getBitmap(strings[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}

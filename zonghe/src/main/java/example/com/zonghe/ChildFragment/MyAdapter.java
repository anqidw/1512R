package example.com.zonghe.ChildFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import example.com.zonghe.R;
import example.com.zonghe.bean.NetHttpurlConnection;
import example.com.zonghe.bean.UserData;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class MyAdapter extends BaseAdapter{
    private List<UserData.DataBean> list;
    private Context context ;

    public MyAdapter(List<UserData.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
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
            view=View.inflate(context, R.layout.list_layout,null);
            holder=new ViewHolder();
            holder.name= view.findViewById(R.id.name);
            holder.img=view.findViewById(R.id.img);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.name.setText(list.get(i).getNews_summary());
        //获取图片
        new MyAsyncTask(holder.img).execute(list.get(i).getPic_url());
        return view;
    }
    class ViewHolder{
        private TextView name;
        private ImageView img;
    }

    private class MyAsyncTask extends AsyncTask<String ,Void ,Bitmap>{
        private final ImageView img;

        public MyAsyncTask(ImageView img) {
            this.img=img;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = NetHttpurlConnection.getBitmap(strings[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //赋值
            img.setImageBitmap(bitmap);
        }
    }
}

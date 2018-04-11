package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    //获取网络数据地址
    public String urlString="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";


    private ImageView imageView;
    private TextView textView;
     Handler handler = new Handler(){
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             int what=msg.what;
             if(what==1){
                String s = (String) msg.obj;
                textView.setText(s);
             }
         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件ID
         imageView= findViewById(R.id.image1);
         textView=findViewById(R.id.text1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){

                    public void run(){
                        try {
                            //获取路径
                            URL url = new URL(urlString);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            int responseCode = urlConnection.getResponseCode();
                            if(responseCode==200){
                                InputStream inputStream = urlConnection.getInputStream();
                                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                                StringBuilder stringBuilder=new StringBuilder();
                                String a="";
                                while((a=bufferedReader.readLine())!=null){
                                    stringBuilder.append(a);
                                }
                                String s = stringBuilder.toString();
                            //    textView.setText(s);
                                Message message=new Message();
                                message.what=1;
                                message.obj=s;
                                handler.sendMessage(message);
                            }else{

                            }

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }.start();
            }
        });



        //设置监听时间


    }
}

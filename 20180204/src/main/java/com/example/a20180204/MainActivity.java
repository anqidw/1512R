package com.example.a20180204;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



   public String Stringurl="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
  String Bitmipurl="http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg";
    private ImageView imageView;
    private TextView textView;
     Handler handler = new Handler(){
    //xxxxxxxx
    @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             if(msg.what==1){
                String json= (String) msg.obj;
                textView.setText(json);
             }else if(msg.what==2){
                 Bitmap bitmap= (Bitmap) msg.obj;
                 imageView.setImageBitmap(bitmap);
             }
         }
     };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_04);
        imageView = findViewById(R.id.image_04);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new Thread(){

                   public void  run(){
                       String json = Utils.getNetjson(Stringurl);
                       Message message = new Message();
                       message.obj=json;

                       message.what=1;
                       handler.sendMessage(message);
                   }

               }.start();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              new Thread(){
                  public void run(){
                      Bitmap bitmap = Utils.getNetbitmap(Bitmipurl);
                      Message message = new Message();
                      message.what=2;
                      message.obj=bitmap;
                      handler.sendMessage(message);
                  }
                  
                  
              }.start();
            }
        });

    }
}

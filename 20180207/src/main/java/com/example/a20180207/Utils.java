package com.example.a20180207;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author:Created by WangZhiQiang on 2018/2/7.
 */

public class Utils {

    public static String getNetjson(String urlString){

        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode==200){
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                String temp="";
                while((temp=bufferedReader.readLine())!=null){
                      stringBuilder.append(temp);
                }
                String result=stringBuilder.toString();
                return  result;
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  "";
    }
//获取网络图片
    public static Bitmap  getNetbitmap(String urlstring){

        try {
            URL url = new URL(urlstring);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode==200){
                InputStream inputStream = httpURLConnection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                return  bitmap;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }
 }
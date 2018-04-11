package com.example.a01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author:Created by anqi 2018/2/22.
 */

public class Utils  {
    public static  String getNetJson(String stringurl){

        try {
            URL url = new URL(stringurl);
          HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
        if(responseCode==200){
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String temp="";

            while((temp=bufferedReader.readLine())!=null){
                    stringBuilder.append(temp);
            }
            String is=stringBuilder.toString();
            return  is;

        }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  "";
    }



    public static Bitmap getNetBitmap(String urlString) {

        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
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

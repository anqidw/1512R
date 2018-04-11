package com.example.anqizkao2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author:Created by WangZhiQiang on 2018/2/23.
 */

public class Utils {

    private URL url;

    public  static   String  getNetString(String stringurl){

        try {
            URL url = new URL(stringurl);
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

         String  aa=stringBuilder.toString();
         return  aa;



     }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}

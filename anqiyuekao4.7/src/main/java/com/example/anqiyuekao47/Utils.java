package com.example.anqiyuekao47;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author:Created by WangZhiQiang on 2018/4/7.
 */

public class Utils {
    public static  String getnetjson(String uristring){


        try {
            URL url = new URL(uristring);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode==200){
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String temp="";
                while((temp=bufferedReader.readLine())!=null){
                    stringBuffer.append(temp);
                }
                String s = stringBuffer.toString();
                return  s;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  "";

    }




}

package com.example.anqizhoukao1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Utils {

    public static String getNetConnection(String urlString){

        try {

            URL url = new URL(urlString);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode=httpURLConnection.getResponseCode();
            if(responseCode==200){
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String temp="";
                while((temp=bufferedReader.readLine())!=null){
                          stringBuilder.append(temp);
                }
                String data=stringBuilder.toString();
                return  data;
            }

        } catch (IOException e) {

            e.printStackTrace();
        }


        return "";
    }









}

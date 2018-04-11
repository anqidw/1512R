package com.example.a20180209;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.util.EntityUtils;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

/**
 * author:Created by WangZhiQiang on 2018/2/10.
 */

public class Bean  {
    public  static  String getNetjson(String urlString){

        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode ==200){

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                String temp="";
                while((temp=bufferedReader.readLine())!=null){

                    stringBuilder.append(temp);
                }
                String resurt=stringBuilder.toString();
                return  resurt;



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }


    public static Bitmap getBitmap(String urlBitmap){
        try {
            URL url = new URL(urlBitmap);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if(responseCode==200){
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getHttpClient(String urlHttpClient){
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(urlHttpClient);
        try {
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            int statusCode = execute.getStatusLine().getStatusCode();
            if(statusCode==200){
                HttpEntity entity = execute.getEntity();
                String string = EntityUtils.toString(entity);
                return string;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}

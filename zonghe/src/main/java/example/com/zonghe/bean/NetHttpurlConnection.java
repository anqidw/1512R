package example.com.zonghe.bean;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class NetHttpurlConnection {
    //请求网络文字
    public static String getText(String urlString){
     try {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        int responseCode = urlConnection.getResponseCode();
        if(responseCode==200){
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String temp="";
            while ((temp=bufferedReader.readLine())!=null){
                stringBuilder.append(temp);
            }
            String result = stringBuilder.toString();
            return result;

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        return "";
    }
    //请求网络图片
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
}

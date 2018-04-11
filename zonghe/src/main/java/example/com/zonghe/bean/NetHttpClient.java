package example.com.zonghe.bean;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * author:Created by WangZhiQiang on 2018/1/30.
 */

public class NetHttpClient {
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

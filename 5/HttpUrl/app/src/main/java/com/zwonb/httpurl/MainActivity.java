package com.zwonb.httpurl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        get请求访问百度首页
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                useHttpClientGet("http://www.baidu.com");
//            }
//        }).start();

//        post请求访问淘宝IP地址库
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                useHttpClientPost("http://ip.taobao.com/service/getIpInfo.php");
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                useHttpUrlConnetionPost("http://ip.taobao.com/service/getIpInfo.php");
            }
        }).start();

    }

    /**
     * 创建HttpClient
     */
    private HttpClient createHttpClient() {
        HttpParams mDefaultHttpParams = new BasicHttpParams();
//        设置连接超时
        HttpConnectionParams.setConnectionTimeout(mDefaultHttpParams, 15000);
//        设置请求超时
        HttpConnectionParams.setSoTimeout(mDefaultHttpParams, 15000);
        HttpConnectionParams.setTcpNoDelay(mDefaultHttpParams, true);
        HttpProtocolParams.setVersion(mDefaultHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(mDefaultHttpParams, HTTP.UTF_8);
//        持续握手
        HttpProtocolParams.setUseExpectContinue(mDefaultHttpParams, true);
        return new DefaultHttpClient(mDefaultHttpParams);
    }

    private void useHttpClientGet(String url) {
        HttpGet mHttpGet = new HttpGet(url);
        mHttpGet.addHeader("Connection", "Keep-Alive");
        HttpClient mHttpClient = createHttpClient();
        try {
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpGet);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if (mHttpEntity != null) {
                InputStream mInputStream = mHttpEntity.getContent();
                String respose = converStreamToString(mInputStream);
                Log.e("binbin", "请求状态码：" + code + "\r\n请求结果：\r\n" + respose);
                mInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将请求结果转换成String
     */
    private String converStreamToString(InputStream mInputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        StringBuilder stringBuffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line).append("\r\n");
        }
        return stringBuffer.toString();
    }


    private void useHttpClientPost(String url){
        HttpPost mHttpPost = new HttpPost(url);
        mHttpPost.addHeader("Connection", "Keep-Alive");
        HttpClient mHttpClient = createHttpClient();
        List<NameValuePair> postParams = new ArrayList<>();
//        要传递的参数
        postParams.add(new BasicNameValuePair("ip","14.20.130.165"));
        try {
            mHttpPost.setEntity(new UrlEncodedFormEntity(postParams));
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if (mHttpEntity != null) {
                InputStream mInputStream = mHttpEntity.getContent();
                String respose = converStreamToString(mInputStream);
                Log.e("binbin", "请求状态码：" + code + "\r\n请求结果：\r\n" + respose);
                mInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void useHttpUrlConnetionPost(String url) {
        InputStream mInputStream = null;
        HttpURLConnection mHttpURLConnection = UrlConnManager.getHttpURLConnection(url);
        List<NameValuePair> postParams = new ArrayList<>();
        postParams.add(new BasicNameValuePair("ip","14.20.130.165"));
        try {
            UrlConnManager.postParams(mHttpURLConnection.getOutputStream(), postParams);
            mHttpURLConnection.connect();
            mInputStream = mHttpURLConnection.getInputStream();
            int code = mHttpURLConnection.getResponseCode();
            String respose = converStreamToString(mInputStream);
            Log.e("binbin", "请求状态码：" + code + "\r\n请求结果：\r\n" + respose);
            mInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.zwonb.myvolley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private ImageView mImageView;
    private TextView mTextView;
    private NetworkImageView nv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.content);
        mImageView = (ImageView) findViewById(R.id.img);
        nv_image = (NetworkImageView) findViewById(R.id.nv_image);
        mQueue = Volley.newRequestQueue(getApplicationContext());
//        useStringRequest();
        useJsonRequest();
//        useImageRequest();
        useImageLoader();
        useNetworkImageView();
    }

    private void useStringRequest() {
        //创建请求队列
        StringRequest mRequest = new StringRequest(Request.Method.GET, "https://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView.setText(response);
                        Log.e("binbin", "onResponse: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("binbin", "onErrorResponse: " + error.getMessage() + error);
                    }
                });
        //将请求添加在请求队列中
        mQueue.add(mRequest);
    }

    private void useJsonRequest() {
        String requestBody = "ip=14.20.130.165";
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                "http://ip.taobao.com/service/getIpInfo.php?ip=14.20.130.165",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String mJson = new Gson().toJson(response);
                        IpModel mIpModel = new Gson().fromJson(mJson, IpModel.class);
                        if (mIpModel != null && mIpModel.getData() != null) {
                            String mCity = mIpModel.getData().getCity();
                            mTextView.setText(mCity);
                            Log.e("binbin", "onResponse: " + mCity);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("binbin", "onErrorResponse: " + error.getMessage() + error);
                    }
                });
        mQueue.add(mJsonObjectRequest);
    }

    private void useImageRequest() {
        ImageRequest mImageRequest = new ImageRequest("http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        mImageView.setImageBitmap(response);
                    }
                },
                0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mImageView.setImageResource(R.mipmap.pic_error);
                    }
                });
        mQueue.add(mImageRequest);
    }

    private void useImageLoader() {
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(mImageView, R.mipmap.pic_error, R.mipmap.pic_error);
        imageLoader.get("http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg", listener);
    }

    private void useNetworkImageView() {
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        nv_image.setDefaultImageResId(R.mipmap.pic_error);
        nv_image.setErrorImageResId(R.mipmap.pic_error);
        nv_image.setImageUrl("http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg",
                imageLoader);

    }

}

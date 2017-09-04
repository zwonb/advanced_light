package com.zwonb.myretrofit;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getRequest();
//        getPath();
//        postRequest();
        postFile();

    }

    /**
     * get请求
     */
    private void getRequest() {
        String url = "http://v.juhe.cn/toutiao/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
//                添加返回值为Json的支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IpService service = retrofit.create(IpService.class);
        Call<IpModel> call = service.getIpMsg("top", "f73e78527001c8928b0788683852ac96");
        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                Log.e("binbin", "onResponse: " + response.body().getReason());
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
                Log.e("binbin", "onFailure: " + "请求失败");
            }
        });

    }

    /**
     * get请求
     */
    private void getPath() {
        String url = "http://v.juhe.cn/toutiao/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
//                添加返回值为Json的支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IpService service = retrofit.create(IpService.class);
        Call<IpModel> call = service.getPath("index", "top", "f73e78527001c8928b0788683852ac96");
        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                Log.e("binbin", "onResponse: " + response.body().getReason());
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
                Log.e("binbin", "onFailure: " + "请求失败");
            }
        });
    }

    /**
     * post请求
     */
    private void postRequest() {
        String baseUrl = "http://v.juhe.cn/toutiao/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IpService service = retrofit.create(IpService.class);
        Call<IpModel> call = service.postIpMsg("top", "f73e78527001c8928b0788683852ac96");
        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                Log.e("binbin", "onResponse: " + response.body().getReason());
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
                Log.e("binbin", "onFailure: " + "请求失败");
            }
        });
    }

    /**
     * 单个文件上传@Part
     */
    private void postFile() {
        String baseUrl = "http://v.juhe.cn/toutiao/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        File file = new File(Environment.getExternalStorageDirectory(), "zwonb.png");
        final RequestBody requestBody = RequestBody.create(MediaType.parse("image.png"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", "zwonb.png", requestBody);
        IpService ipService = retrofit.create(IpService.class);
        Call<IpModel> call = ipService.postFile(photo, RequestBody.create(null, "zwonb"));
        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                Log.e("binbin", "onResponse: " + "上传成功");
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
                Log.e("binbin", "onResponse: " + "请求失败");
            }
        });

    }


}

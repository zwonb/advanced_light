package com.zwonb.myokhttp3;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final MediaType MEDIA_TYPE_MARKDOWN =
            MediaType.parse("image/png");
    public static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getHttpRequest();
//        postHttpRequest();
//        postHttpFile();
//        downPic();
//        sendMultipart();
//        cancel();

        OkHttpEngine.getInstance(this).getAsynHtpp("http://www.baidu.com", new ResultCallback() {
            @Override
            void onError(Request request, Exception e) {

            }

            @Override
            void onResponse(Response response) throws IOException {
                Log.e("binbin", "onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 异步get请求
     */
    private void getHttpRequest() {
        Request.Builder mBuilder = new Request.Builder().url("http://www.baidu.com");
        mBuilder.method("GET", null);
        Request mRequest = mBuilder.build();
        OkHttpClient mClient = new OkHttpClient();
        Call mCall = mClient.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mString = response.body().string();
                Log.e("binbin", "onResponse: " + mString);

            }
        });
    }

    /**
     * 异步post请求
     */
    private void postHttpRequest() {
        RequestBody formBody = new FormBody.Builder()
                .add("ip", "14.20.130.25")
                .build();
        Request mBuild = new Request.Builder()
                .url("http://ip.taobao.com/service/getIpInfo.php")
                .post(formBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(mBuild);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("binbin", "onFailure: " + "失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("binbin", "onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 异步上传文件
     */
    private void postHttpFile() {
        String filepath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return;
        }
        File file = new File(filepath, "zwonb.png");
        Request request = new Request.Builder()
                .url("http://www.cloudoop.top:18000/homepage/uploadImage/")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("binbin", "onFailure: " + "上传失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("binbin", "onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 异步下载文件
     */
    private void downPic() {
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503571198744&di=b061709223785e86b2292aae6632be0a&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D99bd7ae7a551f3ded7bfb127fc879a6a%2Fb58f8c5494eef01f3e82aae8eafe9925bc317d0c.jpg";
        Request mRequest = new Request.Builder().url(url).build();
        OkHttpClient mClient = new OkHttpClient();
        mClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("binbin", "onFailure: " + "shibai");
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream mInputStream = response.body().byteStream();
                FileOutputStream mFileOutputStream;
                String filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/TestImg/";
                File mFileDir = new File(filepath);
                File mFile = null;
                if (!mFileDir.exists()) {
                    mFileDir.mkdirs();
                }
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
                    } else {
                        filepath = getFilesDir().getAbsolutePath();
                    }
                    mFile = new File(filepath, "zwonb.jpg");
                    mFileOutputStream = new FileOutputStream(mFile);
                    byte[] buffer = new byte[2048];
                    int len;
                    while ((len = mInputStream.read(buffer)) != -1) {
                        mFileOutputStream.write(buffer, 0, len);
                    }
                    mFileOutputStream.flush();
                    mFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 异步上传多个文件
     */
    private void sendMultipart() {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "zwonb")
                .addFormDataPart("image", "zwonb.png", RequestBody.create(MEDIA_TYPE_PNG, new File("/sdcard/zwonb.jpg")))
                .build();
        final Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "...")
                .url("http://www.cloudoop.top:18000/homepage/uploadImage/")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("binbin", "onFailure: " + "上传失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("binbin", "onResponse: " + response.body().string());
            }
        });

    }

    /**
     * 设置超时时间和缓存
     */
    private void setClient() {
        File cacheFile = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(cacheFile.getAbsoluteFile(), cacheSize))
                .build();
    }


    /**
     * 取消请求
     */
    private ScheduledExecutorService mExecutorService = Executors.newScheduledThreadPool(1);

    private void cancel(){
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build();
        Call call = null;
        call = new OkHttpClient().newCall(request);
        final Call finalCall = call;
        mExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                finalCall.cancel();
            }
        }, 1, TimeUnit.MILLISECONDS);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("binbin", "onFailure: " + "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.cacheResponse() != null) {
                    Log.e("binbin", "cache: " + response.body().string());
                }else {
                    Log.e("binbin", "network: " + response.networkResponse().toString());
                }
            }
        });

    }

}

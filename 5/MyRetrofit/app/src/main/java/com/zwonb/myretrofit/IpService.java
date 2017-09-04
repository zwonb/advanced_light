package com.zwonb.myretrofit;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zyb on 2017/9/1.
 */

public interface IpService {

    @GET("index")
    Call<IpModel> getIpMsg(@Query("type") String top, @Query("key") String key);

    @GET("{path}")
    Call<IpModel> getPath(@Path("path") String path, @Query("type") String top, @Query("key") String key);

    @FormUrlEncoded
    @POST("index")
    Call<IpModel> postIpMsg(@Field("type") String type, @Field("key") String key);

    //单个文件上传
    @Multipart
    @POST("index")
    Call<IpModel> postFile(@Part MultipartBody.Part photo, @Part("description") RequestBody description);

    //多个文件上传
    @Multipart
    @POST("index")
    Call<IpModel> postFiles(@PartMap Map<String,RequestBody> photos, @Part("description") RequestBody description);




}

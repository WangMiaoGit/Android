package com.wang.latte.net;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by MaxWang on 2018/9/21.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/21 16:48
 * 修改人  ：MaxWang
 * 修改时间：2018/9/21
 * 修改备注：
 */
//网络请求
public interface RestService {

    /**
     * get的网络请求方法
     *
     * @param url    url地址
     * @param params 以键值对形式传递的参数，拼接到url
     * @return Call回调 和string
     */
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     *  文件一次性下载到内存，然后当下载完毕统一写的文件
     *  Streaming边下载边写入
     *  防止内存溢出
     *  单独线程中执行
     * @param url
     * @param params
     * @return 返回的响应体
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part params);

}

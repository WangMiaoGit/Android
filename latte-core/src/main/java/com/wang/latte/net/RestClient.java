package com.wang.latte.net;

import android.content.Context;

import com.wang.latte.net.callback.IError;
import com.wang.latte.net.callback.IFailure;
import com.wang.latte.net.callback.IRequest;
import com.wang.latte.net.callback.ISuccess;
import com.wang.latte.net.callback.RequestCallbacks;
import com.wang.latte.net.download.DownloadHandler;
import com.wang.latte.ui.LatteLoader;
import com.wang.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Multipart;

/**
 * Created by MaxWang on 2018/9/21.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/21 16:44
 * 修改人  ：MaxWang
 * 修改时间：2018/9/21
 * 修改备注：
 */

//网络 处理类
public class RestClient {

    //final 的变量没有初始化赋值时候，需要在构造函数中赋值
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    private final File FILE;//文件上传
    private final LoaderStyle LOADER_STYTLE;

    private final Context CONTEXT;
    //文件下载
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      File file,
                      Context context,
                      String downloadDir,
                      String extension,
                      String name) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;

        this.FILE= file;
        this.LOADER_STYTLE = loaderStyle;
        this.CONTEXT = context;

        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;

    }

    //构建者模式取到builder
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    /**
     * 发送请求 访问数据
     *
     * @param method 请求的方式
     */
    private void request(HttpMethod method) {
        //获取 网络访问的  service接口
        final RestService service = RestCreator.getRestService();//这个时候retrofit的url是初始化配置的url
        //retrofit  返回的call
        Call<String> call = null;

        //请求 从builder中加入
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        //loader显示的style   从builder中加入
        if (LOADER_STYTLE != null) {//如果给了样式就用 给定样式
            LatteLoader.showLoading(CONTEXT, LOADER_STYTLE);
        }
        //根据传入的枚举类的method  选择调用service
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);//Delegate中传入的url
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;

        }
        if (call != null) {
            //call.execute()主线程中使用
            // call.enqueue()子线程 后台
            call.enqueue(getRequestCallback());//执行网络请求 getRequestCallback()请求回调
        }
    }

    //获取网络执行的回调
    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYTLE
        );
    }



    /**
     * 请求方式
     */
    public final void get() {
        request(HttpMethod.GET);
    }
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }
    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }
    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void download(){
        new DownloadHandler(URL,REQUEST,SUCCESS,FAILURE,ERROR,DOWNLOAD_DIR,EXTENSION,NAME)
                .handleDownload();
    }
}

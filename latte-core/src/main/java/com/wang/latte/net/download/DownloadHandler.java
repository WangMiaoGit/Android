package com.wang.latte.net.download;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.wang.latte.R;
import com.wang.latte.net.RestCreator;
import com.wang.latte.net.callback.IError;
import com.wang.latte.net.callback.IFailure;
import com.wang.latte.net.callback.IRequest;
import com.wang.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.QueryMap;

/**
 * Created by MaxWang on 2018/10/9.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/10/9 11:50
 * 修改人  ：MaxWang
 * 修改时间：2018/10/9
 * 修改备注：
 */

public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    //文件下载
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url, IRequest request, ISuccess success, IFailure failure, IError error, String download_dir, String extension, String name) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    //处理下载的方法
    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();//开始下载
        }

        //调用RestService接口的download下载
        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);


                            final ResponseBody responseBody = response.body();
                            //以线程池方式执行  execute队列方式
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, response, NAME);

                            //一定要判断，否则下载的文件不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if (ERROR!=null){
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE!=null){
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}

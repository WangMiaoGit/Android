package com.wang.latte.net.callback;

import android.os.Handler;

import com.wang.latte.ui.LatteLoader;
import com.wang.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MaxWang on 2018/9/22.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/22 10:23
 * 修改人  ：MaxWang
 * 修改时间：2018/9/22
 * 修改备注：
 */

//网络访问的  call.execute下的 回调
public class RequestCallbacks implements Callback<String> {
    //回调的接口
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    //handler 声明为static类型，可以避免内存泄露
    private static final Handler HANDLER = new Handler();


    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    //两个回调的方法
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());//成功
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());//错误
            }
        }

        if (LOADER_STYLE != null) {
            //延时一秒显示

//            HANDLER.postDelayed(LatteLoader::stopLoading, 1000);
            HANDLER.postDelayed(()->LatteLoader.stopLoading(),1000);//Lambda
//            HANDLER.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    LatteLoader.stopLoading();
//                }
//            }, 1000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();//失败
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();//完成
        }
        stopLoading();
    }

    //停止Loader
    private void stopLoading() {
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(() -> LatteLoader.stopLoading(), 1000);
        }
    }
}

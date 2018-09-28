package com.wang.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.wang.latte.delegate.LatteDelegate;
import com.wang.latte.net.RestClient;
import com.wang.latte.net.callback.IError;
import com.wang.latte.net.callback.IFailure;
import com.wang.latte.net.callback.ISuccess;

/**
 * Created by MaxWang on 2018/9/21.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/21 16:32
 * 修改人  ：MaxWang
 * 修改时间：2018/9/21
 * 修改备注：
 */

/**
 * 根fragment
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    //对控件操作
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();

    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com")//访问的url
                .loader(getContext())//只传context  就给默认样式
//                .loader(LoaderStyle.LineSpinFadeLoaderIndicator,getContext())//给定样式
                .success(new ISuccess() {//接口回调
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "failure", Toast.LENGTH_LONG).show();

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

                    }
                })
                .build()
                .get();//get请求  到RestClient
    }
}

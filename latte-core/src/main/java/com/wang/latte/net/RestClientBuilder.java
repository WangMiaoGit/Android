package com.wang.latte.net;

import android.content.Context;

import com.wang.latte.net.callback.IError;
import com.wang.latte.net.callback.IFailure;
import com.wang.latte.net.callback.IRequest;
import com.wang.latte.net.callback.ISuccess;
import com.wang.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by MaxWang on 2018/9/22.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/22 9:19
 * 修改人  ：MaxWang
 * 修改时间：2018/9/22
 * 修改备注：
 */

//建造者模式，给RestClient传值和回调
public class RestClientBuilder {

    private String mUrl = null;//访问的网址
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;

    private File mFile = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;

    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    //通过直接传map
    public final RestClientBuilder params(Map<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    //传键值对的形式
    public final RestClientBuilder params(String key, Object value) {

        PARAMS.put(key, value);
        return this;
    }

    //传文件
    public final RestClientBuilder fiel(File file) {
        this.mFile = file;
        return this;
    }
    //传字符串
    public final RestClientBuilder fiel(String file) {
        this.mFile = new File(file);
        return this;
    }

    /**
     * 文件下载
     * @param name
     * @return
     */
    public final RestClientBuilder name(String name) {
        this.mName = name;//文件名
        return this;
    }
    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;//下载后文件存放的路径
        return this;
    }
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;//后缀
        return this;
    }


    //传原始json
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    //回调处理
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }


    //加载loader
    public final RestClientBuilder loader(LoaderStyle mLoaderStyle, Context mContext) {
        this.mLoaderStyle = mLoaderStyle;
        this.mContext = mContext;
        return this;
    }
    //默认的style，加载动画的设置
    public final RestClientBuilder loader(Context mContext) {
        this.mLoaderStyle = LoaderStyle.BallPulseIndicator;
        this.mContext = mContext;
        return this;
    }

    //构建出RestClient对象
    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody,mLoaderStyle,mFile,mContext,mDownloadDir, mExtension, mName);
    }

}

package com.wang.latte.net;

import com.wang.latte.app.ConfigType;
import com.wang.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by MaxWang on 2018/9/22.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/22 9:01
 * 修改人  ：MaxWang
 * 修改时间：2018/9/22
 * 修改备注：
 */

//网络访问操作类   获取 网络访问的  service   Retrofit + Okhttp
public class RestCreator {


    //懒加载  获取PARAMS对象，单例
    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    }
    //获取参数
    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    //获取RestService  接口下的网络请求
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    //得到RestService
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    //RetrofitHolder
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());//初始化的host
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    //OKHttpHolder
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


}

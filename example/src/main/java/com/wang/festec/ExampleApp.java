package com.wang.festec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wang.latte.app.Latte;
import com.wang.latte.ec.icon.FountEcModel;

/**
 * Created by MaxWang on 2018/9/20.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/20 16:29
 * 修改人  ：MaxWang
 * 修改时间：2018/9/20
 * 修改备注：
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
//                .withApiHost("http//127.0.0.1/")//最开始没有网络的时候测试可不可以传  初始化的host
                .withApiHost("https://www.baidu.com/")//网络服务器地址
                .withIcon(new FontAwesomeModule())//引入的字体库
                .withIcon(new FountEcModel())//自定义的字体库
                .configure();//初始化
    }
}

package com.wang.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by MaxWang on 2018/9/20.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/20 16:04
 * 修改人  ：MaxWang
 * 修改时间：2018/9/20
 * 修改备注：
 */

public final class Latte {

    //初始化配置  传入context
    public static Configurator init(Context context){
        //HashMap 中传入枚举 和  context
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstence();
    }

    public static HashMap<String,Object> getConfigurations(){
        //拿到Configurator配置类的 HashMap
        return Configurator.getInstence().getLatteConfigs();
    }

    public static  Context getApplication(){
        //获取HashMap 中的  context
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}

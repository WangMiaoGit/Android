package com.wang.latte.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.wang.latte.app.Latte;

/**
 * Created by MaxWang on 2018/9/22.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/22 14:11
 * 修改人  ：MaxWang
 * 修改时间：2018/9/22
 * 修改备注：
 */

//计算宽高的工具类
public class DimenUtil {

    //获取屏幕的宽度
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    //获取屏幕的高度
    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}

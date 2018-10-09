package com.wang.latte.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.latte.R;
import com.wang.latte.util.dimen.DimenUtil;

import java.util.ArrayList;

/**
 * Created by MaxWang on 2018/9/22.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/22 14:01
 * 修改人  ：MaxWang
 * 修改时间：2018/9/22
 * 修改备注：
 */
//loader类  加载动画
public class LatteLoader {
    //缩放比
    private static final int LOADER_SIZE_SCALE = 8;
    //偏移量
    private static final int LOADER_OFFSET_SACLE = 10;
    //默认的style
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotateIndicator.name();

    //用来存储所有的  loader
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    //显示loader   调用showLoading(Context context, String type)
    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    //显示loader
    public static void showLoading(Context context, String type) {
        //以dialog形式弹出  loader      R.style.dialog 设置loger的样式
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        //以缓存的方式拿到了loader
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        //dialog  设置内部的view
        dialog.setContentView(avLoadingIndicatorView);
        //工具类类计算宽高
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeidth = DimenUtil.getScreenHeight();
        //获取dialog的Window对象
        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            //获取布局的参数
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            //宽高比例缩放
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceWidth / LOADER_SIZE_SCALE;
            //偏移量
            lp.height = lp.height + deviceHeidth / LOADER_OFFSET_SACLE;
            //居中显示
            lp.gravity = Gravity.CENTER;
        }

        //对话框集合增加dialog
        LOADERS.add(dialog);
        dialog.show();
    }

    //给一个默认的loader样式
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    //遍历所有的loader  进行关闭
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog.isShowing()) {
                dialog.cancel();
            }
        }
    }
}

package com.wang.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.wang.latte.app.Latte;
import com.wang.latte.net.callback.IRequest;
import com.wang.latte.net.callback.ISuccess;
import com.wang.latte.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by MaxWang on 2018/10/9.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/10/9 11:58
 * 修改人  ：MaxWang
 * 修改时间：2018/10/9
 * 修改备注：
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0];//存放的地址
        String extension = (String) params[1];//后缀
        final ResponseBody body = (ResponseBody) params[2];//请求体
        final String name = (String) params[3];//名称
        final InputStream is = body.byteStream();//输入流
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";//给定的默认的下载目录
        }
        if (extension == null || extension.equals("")) {
            extension = "";//给定默认的后缀
        }
        if (name == null) {//如果没有传文件保存名称
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {//传入了文件保存名称  name完整文件名
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }

        autoInstallApk(file);
    }

    //自动安装apk文件
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplication().startActivity(install);
        }
    }
}

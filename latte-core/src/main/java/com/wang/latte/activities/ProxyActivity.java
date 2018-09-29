package com.wang.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.wang.latte.R;
import com.wang.latte.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by MaxWang on 2018/9/21.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/21 13:52
 * 修改人  ：MaxWang
 * 修改时间：2018/9/21
 * 修改备注：
 */

//基类Activity
public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);

        setContentView(container);
        if (savedInstanceState==null){
            //第一次加载
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}

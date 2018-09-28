package com.wang.festec;

import com.wang.latte.activities.ProxyActivity;
import com.wang.latte.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}

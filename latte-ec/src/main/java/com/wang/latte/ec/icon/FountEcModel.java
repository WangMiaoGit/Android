package com.wang.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by MaxWang on 2018/9/21.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/21 11:49
 * 修改人  ：MaxWang
 * 修改时间：2018/9/21
 * 修改备注：
 */

public class FountEcModel implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}

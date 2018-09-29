package com.wang.latte.ec.icon;


import com.joanzapata.iconify.Icon;

/**
 * Created by MaxWang on 2018/9/21.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/21 11:50
 * 修改人  ：MaxWang
 * 修改时间：2018/9/21
 * 修改备注：
 */

public enum EcIcons implements Icon {
    icon_scan('\ue606'),

    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return character;
    }
}

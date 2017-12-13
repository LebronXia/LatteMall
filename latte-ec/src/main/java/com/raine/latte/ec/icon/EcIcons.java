package com.raine.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by zhengxiaobo on 2017/12/13.
 */

public enum EcIcons implements Icon{

    icon_ceshi('\ue627'),
    icon_shangchuan('\ue607');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}

package com.raine.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 自己定义的图库
 * Created by zhengxiaobo on 2017/12/13.
 */

public class FontEcModule implements IconFontDescriptor{

    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}

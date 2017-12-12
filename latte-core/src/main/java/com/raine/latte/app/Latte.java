package com.raine.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by zhengxiaobo on 2017/12/12.
 */
//Application核心配置类
public class Latte {

    //初始化配置
    public static Configurator init(Context context){
        getConfiguration().put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<Object, Object> getConfiguration(){
        return Configurator.getInstance().getLatteConfigs();
    }
}

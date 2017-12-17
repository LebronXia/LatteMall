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
        Configurator.getInstance().getLatteConfigs().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }



    public static Context getApplicationContext(){
        return (Context) getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}

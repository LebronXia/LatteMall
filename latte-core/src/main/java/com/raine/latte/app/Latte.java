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
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static HashMap<Object, Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }


    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }
}

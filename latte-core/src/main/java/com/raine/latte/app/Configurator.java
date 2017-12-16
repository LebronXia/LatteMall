package com.raine.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 管理配置的类，配置文件的存储和获取
 * Created by zhengxiaobo on 2017/12/12.
 */

public final class Configurator {
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    //存储图标的数组
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    public Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
    }

    public static  Configurator getInstance(){
        return  Holder.INSTANSE;
    }

    private static class Holder{
        //创建单例,懒汉模式
        private static final Configurator INSTANSE = new Configurator();
    }

    //把不需要更改的类 设为final，放置误操作
    final HashMap<Object, Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    //初始化配置
    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    //配置API的数据
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    //初始话icons
    private void initIcons(){
        if (ICONS.size() > 0){
            //取出第一个地址
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    //加入自己的字体图标
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    //核查配置有没有初始化
    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("configuration is not ready, call configure");
        }
    }

    //得到相关的配置
    final <T> T getConfiguration(Enum<ConfigKeys> key){
        checkConfiguration();
        return (T)LATTE_CONFIGS.get(key.name());
    }
}

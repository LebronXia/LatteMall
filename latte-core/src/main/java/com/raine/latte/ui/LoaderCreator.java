package com.raine.latte.ui;

import android.content.Context;
import android.icu.util.IndianCalendar;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.lang.reflect.Type;
import java.util.WeakHashMap;

/**
 * 优化性能，使用缓存的方式创建Loder，不需要在每次使用的时候都去Loader一次
 *
 * Created by Riane on 2017/12/16.
 */

public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView creat(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null){
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name){
        if (name == null || name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")){
            final String defaultPackagetName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackagetName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

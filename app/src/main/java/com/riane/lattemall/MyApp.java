package com.riane.lattemall;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.raine.latte.app.Latte;
import com.raine.latte.ec.icon.FontEcModule;
import com.raine.latte.net.interceptor.DebugInterceptor;

/**
 * Created by zhengxiaobo on 2017/12/12.
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();
        
    }
}

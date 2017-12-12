package com.riane.lattemall;

import android.app.Application;

import com.raine.latte.app.Latte;

/**
 * Created by zhengxiaobo on 2017/12/12.
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("Http://127.0.00.1/")
                .configure();
        
    }
}

package com.raine.latte.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.raine.latte.app.Latte;

/**
 * Created by zhengxiaobo on 2017/12/18.
 */

public class LattePreference {
    //建造了储存类
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());
    private static final String APP_PREFERENCES = "profiles";

    private static SharedPreferences getAppPreference(){
        return PREFERENCES;
    }
    private static void setAppFlag(String key, boolean flag){
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    private static boolean getAppFlag(String key){
        return getAppPreference()
                .getBoolean(key, false);
    }
}

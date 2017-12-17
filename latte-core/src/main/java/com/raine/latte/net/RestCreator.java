package com.raine.latte.net;

import com.raine.latte.app.ConfigKeys;
import com.raine.latte.app.Configurator;
import com.raine.latte.app.Latte;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zhengxiaobo on 2017/12/15.
 */

public class RestCreator {

    //参数容器
    private static final class ParamsHolder{
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    //构建全局Retrofit客户端
    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Latte.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    //创建拦截器
    private static final class OKHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

       private static OkHttpClient.Builder addInterceptor(){
           if (INTERCEPTORS != null && INTERCEPTORS.isEmpty()){
               for (Interceptor interceptor : INTERCEPTORS){
                   BUILDER.addInterceptor(interceptor);
               }
           }
           return BUILDER;
       }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor().connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 创建RestService接口
     */
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

}
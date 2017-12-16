package com.raine.latte.net;

import com.raine.latte.net.callback.IError;
import com.raine.latte.net.callback.IFailure;
import com.raine.latte.net.callback.IRequest;
import com.raine.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.RecursiveTask;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 进行传值的
 * Created by zhengxiaobo on 2017/12/15.
 */

public final class RestClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;

    public RestClientBuilder() {
        super();
    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value){
        PARAMS.put(key, value);
        return this;
    }

    //传入Json格式的数据
    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onResquest(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl, PARAMS
        , mIRequest, mISuccess, mIFailure, mIError, mBody);
    }
}

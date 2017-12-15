package com.raine.latte.net;

import com.raine.latte.net.callback.IError;
import com.raine.latte.net.callback.IFailure;
import com.raine.latte.net.callback.IRequest;
import com.raine.latte.net.callback.ISuccess;
import com.raine.latte.net.callback.RequestCallback;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 网络框架
 * Created by zhengxiaobo on 2017/12/15.
 */

public final class RestClient {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    public RestClient(String URL,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    //获取建造者模式
    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    //根据请求的方法不同
    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
         Call<String> call = null;

        if (REQUEST != null){
            REQUEST.onRequestStart();
        }

        switch (method){
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
//            case UPLOAD:
//                final RequestBody requestBody=
//                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), File);
//                break;
            default:
                break;
        }
        if (call != null){
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallback(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        );
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        if (BODY == null){
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

}

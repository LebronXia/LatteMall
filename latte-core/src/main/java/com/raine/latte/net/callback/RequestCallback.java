package com.raine.latte.net.callback;

import android.content.Loader;
import android.os.Handler;

import com.raine.latte.ui.LatteLoader;
import com.raine.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengxiaobo on 2017/12/15.
 */

public class RequestCallback implements Callback<String>{

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER= new Handler();

    public RequestCallback(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null){
                ERROR.onError(response.code(), response.message());
            }
        }

        if (LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 1000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable throwable) {
        if (FAILURE != null){
            FAILURE.onFailure();
        }
        if (REQUEST != null){
            REQUEST.onRequestEnd();
        }
        LatteLoader.stopLoading();

    }
}

package com.raine.latte.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengxiaobo on 2017/12/15.
 */

public class RequestCallback implements Callback<String>{

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FALURE;
    private final IError ERROR;

    public RequestCallback(IRequest REQUEST, ISuccess SUCCESS, IFailure FALURE, IError ERROR) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FALURE = FALURE;
        this.ERROR = ERROR;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable throwable) {
        if (FALURE != null){
            FALURE.onFailure();
        }
        if (REQUEST != null){
            REQUEST.onRequestEnd();
        }


    }
}

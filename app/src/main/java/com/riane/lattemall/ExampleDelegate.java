package com.riane.lattemall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.raine.latte.delegates.LatteDelegate;
import com.raine.latte.net.RestClient;
import com.raine.latte.net.callback.IError;
import com.raine.latte.net.callback.IFailure;
import com.raine.latte.net.callback.ISuccess;

/**
 * Created by zhengxiaobo on 2017/12/13.
 */

public class ExampleDelegate extends LatteDelegate{

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();;
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                }).build()
        .get();
    }
}

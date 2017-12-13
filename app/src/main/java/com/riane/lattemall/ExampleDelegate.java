package com.riane.lattemall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.raine.latte.delegates.LatteDelegate;

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

    }
}

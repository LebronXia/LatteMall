package com.raine.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.raine.latte.delegates.LatteDelegate;

import java.util.ArrayList;

/**
 * Created by zhengxiaobo on 2017/12/18.
 */

public class LauncherScrollDelegate extends LatteDelegate{

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}

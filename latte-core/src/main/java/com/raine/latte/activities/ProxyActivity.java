package com.raine.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.raine.latte.R;
import com.raine.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 单Activity框架模式
 * Created by zhengxiaobo on 2017/12/13.
 */
public abstract class ProxyActivity extends SupportActivity{
    public abstract LatteDelegate setRootDelegare();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(Bundle saveInstanceState){
        final ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        //创建关键不会重复的id
        contentFrameLayout.setId(R.id.delegate_container);
        setContentView(contentFrameLayout);
        if (saveInstanceState == null){
            //加载根Delegare
            loadRootFragment(R.id.delegate_container, setRootDelegare());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}

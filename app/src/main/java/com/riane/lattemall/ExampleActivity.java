package com.riane.lattemall;

import android.support.v7.app.ActionBar;
import android.os.Bundle;

import com.raine.latte.activities.ProxyActivity;
import com.raine.latte.app.Latte;
import com.raine.latte.delegates.LatteDelegate;
import com.raine.latte.ec.sign.SignUpDelegate;

import org.jetbrains.annotations.Nullable;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegare() {
        return new SignUpDelegate();
    }
}

package com.riane.lattemall;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.raine.latte.activities.ProxyActivity;
import com.raine.latte.app.Latte;
import com.raine.latte.delegates.LatteDelegate;
import com.raine.latte.ec.launcher.LauncherDelegate;
import com.raine.latte.ec.launcher.LauncherScrollDelegate;

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
        return new LauncherDelegate();
    }
}

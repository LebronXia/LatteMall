package com.riane.lattemall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.raine.latte.activities.ProxyActivity;
import com.raine.latte.app.Latte;
import com.raine.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegare() {
        return new ExampleDelegate();
    }
}

package com.raine.latte.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * 多建类，少写方法
 * Created by zhengxiaobo on 2017/12/20.
 */

public class LauncherHolder implements Holder<Integer>{
    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int i, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
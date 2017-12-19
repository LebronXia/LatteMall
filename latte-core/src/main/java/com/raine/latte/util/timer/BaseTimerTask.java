package com.raine.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by zhengxiaobo on 2017/12/18.
 */

public class BaseTimerTask extends TimerTask{

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}

package com.sdh.interviewvideotest;

import android.app.IntentService;
import android.content.Intent;
import android.content.SyncStatusObserver;

/**
 * Created by sdh on 2016/10/18.
 */

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService (){
        super("xxx");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("test"+ Thread.currentThread().getId());

    }
}

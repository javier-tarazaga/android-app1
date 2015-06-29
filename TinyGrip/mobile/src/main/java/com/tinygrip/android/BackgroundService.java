package com.tinygrip.android;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Nazar Zherebetskyi on 28.06.2015.
 */
public class BackgroundService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

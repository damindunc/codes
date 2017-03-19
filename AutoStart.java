package com.cr.support;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by daminduweerasooriya on 3/7/17.
 */

public class AutoStart extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent i = new Intent(context, BackgroundService.class);
            context.startService(i);
        }
    }

}


/*
<receiver
            android:name=".support.AutoStart"
            android:enabled="true"
            android:exported="true">
            <intent-filter android:priority="500">
                <action android:name="android.intent.action.BOOT_COMPLETED" />
            </intent-filter>
        </receiver>
        */
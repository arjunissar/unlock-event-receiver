package com.example.graphics.unlockbrtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by graphics on 9/20/2016.
 */
public class UnlockReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Unlock Successful!",Toast.LENGTH_SHORT).show();
    }
}

package com.kalenicz.maciej.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by RENT on 2017-08-07.
 */

public class BatteryBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Charger plugged in", Toast.LENGTH_SHORT).show();
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startActivityIntent);
    }
}

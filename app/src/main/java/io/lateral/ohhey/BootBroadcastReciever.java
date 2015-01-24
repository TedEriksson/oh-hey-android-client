package io.lateral.ohhey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Ted Eriksson on 24/01/15.
 */
public class BootBroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Reciever", "Boot broadcast recieved, starting background service");
        context.startService(new Intent(context, BackgroundService_.class));
    }
}

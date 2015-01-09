package sailloft.ribbit.utilis;

import android.content.Context;
import android.content.Intent;

import com.parse.ParsePushBroadcastReceiver;

import sailloft.ribbit.ui.MainActivity;

/**
 * Created by David's Laptop on 12/30/2014.
 */
public class Receiver extends ParsePushBroadcastReceiver {

    @Override
    public void onPushOpen(Context context, Intent intent){
        Intent i = new Intent(context, MainActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


}

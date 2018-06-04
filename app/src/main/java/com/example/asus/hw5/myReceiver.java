package com.example.asus.hw5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by ASUS on 2017/10/26.
 */

public class myReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("DYNAMICACTION")) {
            Bundle bundle = intent.getExtras();
            final String name = bundle.getString("name");
            final String price = bundle.getString("price");
            final String type = bundle.getString("type");
            final String data = bundle.getString("data");
            final int srcId = bundle.getInt("srcId");
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("马上下单")
                    .setContentText(name + "已添加到购物车")
                    .setTicker("您有一条新消息")
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), srcId))
                    .setSmallIcon(srcId)
                    .setAutoCancel(true);

            Intent mIntent = new Intent(context, MainActivity.class);
            Bundle bundle1 = new Bundle();
            bundle1.putString("name", name);
            bundle1.putString("price", price);
            bundle1.putString("type", type);
            bundle1.putString("data", data);
            bundle1.putInt("srcId", srcId);
            mIntent.putExtras(bundle1);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(mPendingIntent);

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = builder.build();
            manager.notify(0, notify);
        }
    }
}

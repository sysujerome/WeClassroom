package com.example.asus.hw5;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ASUS on 2017/10/22.
 */

public class buildingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildings);
        final Bundle bundle1 = this.getIntent().getExtras();
        if (bundle1 != null) {
            final int src1 = bundle1.getInt("srcId");
            final String name1 = bundle1.getString("name");
            final int capacity1 = bundle1.getInt("capacity");
            final String price1 = bundle1.getString("price");
            final String type1 = bundle1.getString("type");
            final String data1 = bundle1.getString("data");
            ImageView background = (ImageView) findViewById(R.id.building_image);
            TextView building_name = (TextView) findViewById(R.id.building_name);
            final TextView building_price = (TextView) findViewById(R.id.price1);
            TextView building_type = (TextView) findViewById(R.id.type);
            TextView building_data = (TextView) findViewById(R.id.data);

            background.setImageResource(src1);
            building_name.setText(name1);
            building_price.setText(price1);
            building_type.setText(type1);
            building_data.setText(data1);

            final ImageView stars = (ImageView) findViewById(R.id.star);
            stars.setTag("1");
            stars.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (stars.getTag() == "1") {
                        stars.setImageResource(R.mipmap.full_star);
                        stars.setTag("0");
                    } else {
                        stars.setImageResource(R.mipmap.empty_star);
                        stars.setTag("1");
                    }
                }
            });


            final ImageView backbut = (ImageView) findViewById((R.id.back));
            backbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


            final ImageView shopcar = (ImageView) findViewById(R.id.init_item);
            shopcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buildingList building = new buildingList(name1,capacity1 ,src1,type1, data1,price1);
                    EventBus.getDefault().post(building);
                    IntentFilter dynamic_filter = new IntentFilter();
                    dynamic_filter.addAction("DYNAMICACTION");
                    myReceiver my_Receiver = new myReceiver();
                    registerReceiver(my_Receiver,dynamic_filter);
                    Intent intentBroadcast = new Intent("DYNAMICACTION");
                    Bundle bundleBroadcast = new Bundle();
                    bundleBroadcast.putString("name", name1);
                    bundleBroadcast.putString("price", price1);
                    bundleBroadcast.putString("type", type1);
                    bundleBroadcast.putString("data", data1);
                    bundleBroadcast.putInt("srcId", src1);
                    intentBroadcast.putExtras(bundleBroadcast);
                    sendBroadcast(intentBroadcast);
                    Toast.makeText(buildingActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                }
            });


        }

    }
}

package com.example.asus.hw5;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

public class MainActivity extends AppCompatActivity {
    protected myReceiver my_Receiver;
    private List<Map<String, Object>> listview = new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    private RecyclerView recyclerView;
    private ListView listView;
    private FloatingActionButton fltbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<buildingList> buildinglist = new ArrayList<>();
        buildinglist.add(new buildingList("A101",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节","开班会"));
        buildinglist.add(new buildingList("A102",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节", "数据院团委某部开会"));
        buildinglist.add(new buildingList("A103",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节", "喝酒买酒"));
        buildinglist.add(new buildingList("A104",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节", "夜不归宿"));
        buildinglist.add(new buildingList("A105",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节", "打代码"));
        buildinglist.add(new buildingList("A201",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节", "学习"));
        buildinglist.add(new buildingList("A202",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节", "学习"));
        buildinglist.add(new buildingList("A203",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节", "学习"));
        buildinglist.add(new buildingList("A204",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节", "学习"));
        buildinglist.add(new buildingList("A205",130, R.mipmap.sysu, "帅哥龙","5月20号11-13节", "学习"));

        final String[] name = new String[10];
        final int[] capacity = new int[10];
        final int[] srcid = new  int[10];
        final String[] type = new  String[10];
        final String[] datas = new  String[10];
        final String[] price = new String[10];
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++) {
            buildingList temp = buildinglist.get(i);
            name[i] = temp.getName();
            capacity[i] = temp.getCapacity();
            srcid[i] = temp.getSrc();
            type[i] = temp.getType();
            datas[i] = temp.getData();
            price[i] = temp.getPrice();
            Map<String, Object> tmap = new LinkedHashMap<>();
            tmap.put("name", temp.getName());
            tmap.put("capacity", temp.getCapacity());
            list.add(tmap);
        }
        EventBus.getDefault().register(this);

        final commonAdapter commonadapter;
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commonadapter = new commonAdapter<Map<String, Object>>(this, R.layout.buildinglist, list) {
            @Override
            public void convert(ReViewHolder hodlder, Map<String, Object> t) {
                TextView name = hodlder.getView(R.id.room_name);
                name.setText(t.get("name").toString());
                TextView capacity = hodlder.getView(R.id.room_capacity);
                capacity.setText(t.get("capacity").toString());
            }
        };

        commonadapter.setOnItemClickListener(new commonAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position) {
                Intent intent1 = new Intent(MainActivity.this, buildingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name[position]);
                bundle.putInt("capacity",capacity[position]);
                bundle.putInt("srcId", srcid[position]);
                bundle.putString("type", type[position]);
                bundle.putString("data", datas[position]);
                bundle.putString("price", price[position]);
                intent1.putExtras(bundle);
                startActivityForResult(intent1, 1);
            }
            @Override
            public void onLongClick(int position) {
                Toast.makeText(MainActivity.this, "移除第" + position + "个商品", Toast.LENGTH_SHORT).show();
                commonadapter.removeItem(position);
            }
        });
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(commonadapter);
        animationAdapter.setDuration(1000);
        recyclerView.setAdapter(animationAdapter);
        recyclerView.setItemAnimator(new OvershootInLeftAnimator());

        listView = (ListView) findViewById(R.id.list_view);
//        List<Map<String, Object>> listview = new ArrayList<>();
        Map<String, Object> title1 = new LinkedHashMap<>();
        title1.put("name", "*");
        title1.put("capacity", "课室容量");
        title1.put("type", "原因");
        listview.add(title1);

        simpleAdapter = new SimpleAdapter(this, listview, R.layout.itemlist,
                new String[] {"name","capacity","type"}, new int[] {R.id.room_name, R.id.room_capacity, R.id.type});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (pos != 0) {
                    Intent intent1 = new Intent(MainActivity.this, buildingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", listview.get(pos).get("name").toString());
                    bundle.putInt("capacity", (int) listview.get(pos).get("capacity"));
                    bundle.putString("type", listview.get(pos).get("type").toString());
                    intent1.putExtras(bundle);
                    startActivityForResult(intent1, 1);
                }
            }
        });




        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                if (pos != 0) {
                    final AlertDialog.Builder alerDialog = new AlertDialog.Builder(MainActivity.this);
                    alerDialog.setTitle("移除商品").setMessage("从购物车移除" + listview.get(pos).get("name").toString() + "?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            listview.remove(pos);
                            simpleAdapter.notifyDataSetChanged();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                }
                return true;
            }
        });



        fltbut = (FloatingActionButton) findViewById(R.id.float_but);
        fltbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView.getVisibility() == View.VISIBLE) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    fltbut.setImageResource(R.mipmap.mainpage);
                } else {
                    listView.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    fltbut.setImageResource(R.mipmap.shoplist);
                }
            }
        });


        Random random = new Random();
        int t = random.nextInt(10);
        Intent intentBroadcast = new Intent("STATICACTION");
        Bundle bundleBroadcast = new Bundle();
        bundleBroadcast.putString("name",name[t]);
        bundleBroadcast.putInt("capacity", capacity[t]);
        bundleBroadcast.putInt("srcId",srcid[t]);
        bundleBroadcast.putString("price", price[t]);
        bundleBroadcast.putString("data", datas[t]);
        bundleBroadcast.putString("type", type[t]);
        intentBroadcast.putExtras(bundleBroadcast);
        sendBroadcast(intentBroadcast);

    }


    @Subscribe
    public void onEventMainThred(buildingList building) {
        Map<String, Object> item = new LinkedHashMap<>();
        item.put("name", building.getName());
        item.put("capacity", building.getCapacity());
        item.put("srcId", building.getSrc());
        item.put("price", building.getPrice());
        item.put("data", building.getData());
        item.put("type", building.getType());
        listview.add(item);
        simpleAdapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
        fltbut.setImageResource(R.mipmap.mainpage);
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        if (my_Receiver != null) {
            unregisterReceiver(my_Receiver);
            EventBus.getDefault().unregister(this);
        }
    }
}

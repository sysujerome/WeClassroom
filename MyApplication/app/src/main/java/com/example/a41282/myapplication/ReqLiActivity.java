package com.example.a41282.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ReqLiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_li);
        List<ReqList> reqlist = new ArrayList<>();
        reqlist.add(new ReqList("2018-04-30", "申请中", "2018-05-12", "B403",
                "第5-9节", "杨玉楠", "15331368", "13318707450", "申请教室"));
        reqlist.add(new ReqList("2018-05-01", "申请中", "2018-05-13", "B303",
                "第4-6节", "杨小楠", "15331366", "13318707450", "申请教室"));
        reqlist.add(new ReqList("2018-05-02", "申请中", "2018-05-14", "A402",
                "第4-9节", "杨大楠", "15331363", "13318707450", "申请教室"));
        reqlist.add(new ReqList("2018-05-03", "申请中", "2018-05-15", "D103",
                "第5-7节", "杨啊楠", "15331362", "13318707450", "申请教室"));

        final String[] req_time = new String[12];
        final String[] status = new String[12];
        final String[] req_class_time = new String[12];
        final String[] classroom = new String[12];
        final String[] det_time = new String[12];
        final String[] req_name = new String[12];
        final String[] classID = new String[12];
        final String[] telnum = new String[12];
        final String[] detail = new String[12];
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ReqList temp = reqlist.get(i);
            req_time[i] = temp.getreq_time();
            status[i] = temp.getstatus();
            req_class_time[i] = temp.getreq_class_time();
            classroom[i] = temp.getclassroom();
            det_time[i] = temp.getdet_time();
            req_name[i] = temp.getreq_name();
            classID[i] = temp.getclassID();
            telnum[i] = temp.gettelnum();
            detail[i] = temp.getdetail();
            Map<String, Object> tmap = new LinkedHashMap<>();
            tmap.put("req_time", temp.getreq_time());
            tmap.put("status", temp.getstatus());
            tmap.put("req_name", temp.getreq_name());
            tmap.put("req_class_time", temp.getreq_class_time());
            tmap.put("classroom", temp.getclassroom());
            list.add(tmap);
        }

        final CommonAdapter commonadapter;
        final RecyclerView recyclerView =  findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commonadapter = new CommonAdapter<Map<String, Object>>(this,
                R.layout.list_view_item, list) {
            @Override
            public void convert(MyViewHolder hodlder, Map<String, Object> t) {
                TextView reqtime = hodlder.getViews(R.id.req_time);
                reqtime.setText(t.get("req_time").toString());
                TextView statuss = hodlder.getViews(R.id.req_status);
                statuss.setText(t.get("status").toString());
                TextView req_name = hodlder.getViews(R.id.req_name);
                req_name.setText(t.get("req_name").toString());
                TextView req_class_time = hodlder.getViews(R.id.req_class_time);
                req_class_time.setText(t.get("req_class_time").toString());
                TextView classroom = hodlder.getViews(R.id.classroom);
                classroom.setText(t.get("classroom").toString());
            }
        };
        commonadapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent1 = new Intent(ReqLiActivity.this, ReqDetActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("req_time", req_time[position]);
                bundle.putString("status", status[position]);
                bundle.putString("req_class_time", req_class_time[position]);
                bundle.putString("classroom", classroom[position]);
                bundle.putString("det_time", det_time[position]);
                bundle.putString("req_name", req_name[position]);
                bundle.putString("classID", classID[position]);
                bundle.putString("telnum", telnum[position]);
                bundle.putString("detail", detail[position]);
                intent1.putExtras(bundle);
                startActivityForResult(intent1, 1);
            }
        });

        recyclerView.setAdapter(commonadapter);
    }
}


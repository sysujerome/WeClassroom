package com.example.a41282.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ReqDetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_det);

        final Bundle bundle1 = this.getIntent().getExtras();
        if (bundle1 != null) {
            final String req_time1 = bundle1.getString("req_time");
            final String req_name1 = bundle1.getString("req_name");
            final String status1 = bundle1.getString("status");
            final String req_class_time1 = bundle1.getString("req_class_time");
            final String det_time1 = bundle1.getString("det_time");
            final String classroom1 = bundle1.getString("classroom");
            final String classID1 = bundle1.getString("classID");
            final String telnum1 = bundle1.getString("telnum");
            final String detail1 = bundle1.getString("detail");

            TextView req_time = findViewById(R.id.req_time);
            TextView req_name =  findViewById(R.id.req_name);
            TextView status = findViewById(R.id.status);
            TextView det_class_Time = findViewById(R.id.det_class_time);
            TextView det_time = findViewById(R.id.det_time);
            TextView classroom =  findViewById(R.id.classroom);
            TextView classID =  findViewById(R.id.classID);
            TextView telnum =  findViewById(R.id.telnum);
            TextView detail = findViewById(R.id.detail);

            req_time.setText(req_time1);
            req_name.setText(req_name1);
            status.setText(status1);
            det_class_Time.setText(req_class_time1);
            det_time.setText(det_time1);
            classroom.setText(classroom1);
            classID.setText(classID1);
            telnum.setText(telnum1);
            detail.setText(detail1);
        }
        final ImageView backbut = findViewById((R.id.back));
        backbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}

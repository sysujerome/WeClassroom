package com.example.a41282.myapplication;

public class ReqList {
    private String req_time;
    private String req_class_time;
    private String classID;
    private String req_name;
    private String telnum;
    private String detail;
    private String det_time;
    private String classroom;
    private String status;

    public ReqList(String a, String b, String c, String d, String e, String f,
                   String g, String h, String i) {
        req_time = a;
        status = b;
        req_class_time = c;
        classroom = d;
        det_time = e;
        req_name = f;
        classID = g;
        telnum = h;
        detail = i;
    }

    public String getreq_time() {
        return req_time;
    }

    public String getstatus() {
        return status;
    }

    public String getreq_class_time() {
        return req_class_time;
    }

    public String getclassroom() {
        return classroom;
    }

    public String getdet_time() {
        return det_time;
    }

    public String getreq_name() {
        return req_name;
    }

    public String getclassID() {
        return classID;
    }

    public String gettelnum() {
        return telnum;
    }

    public String getdetail() {
        return detail;
    }
}

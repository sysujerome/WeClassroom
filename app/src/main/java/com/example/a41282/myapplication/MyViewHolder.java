package com.example.a41282.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;  //存储list_view_item的子View
    private View mConvertView;  //存储list_view_item

    public MyViewHolder(Context context, View itemview, ViewGroup parent) {
        super(itemview);
        mConvertView = itemview;
        mViews = new SparseArray<View>();
    }
    //获取ViewHolder实例
    public static MyViewHolder get(Context context, ViewGroup parent, int layoutID) {
        android.view.View itemView = LayoutInflater.from(context).inflate(layoutID, parent,
                false);
        MyViewHolder holder = new MyViewHolder(context, itemView, parent);
        return holder;
    }

    public <T extends View> T getViews(int viewID) {
        View view = mViews.get(viewID);
        if (view == null) {
            //创建View
            view = mConvertView.findViewById(viewID);
            mViews.put(viewID, view);
        }
        return (T) view;
    }
}

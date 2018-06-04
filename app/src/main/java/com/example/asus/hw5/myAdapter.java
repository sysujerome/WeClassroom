package com.example.asus.hw5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ASUS on 2017/10/22.
 */

public class myAdapter extends BaseAdapter {
    private List<buildingList> list;
    private Context context;
    private class ViewHolder {
        public TextView Gfirst;
        public TextView Gname;
        public TextView Gprice;
    }

    public myAdapter(List<buildingList> a, Context b) {
        this.list = a;
        this.context = b;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewgroup) {
        View convertView;
        ViewHolder viewholder;
        if (view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlist, null);
            viewholder = new ViewHolder();
            viewholder.Gfirst = (TextView) convertView.findViewById(R.id.room_capacity);
            viewholder.Gname = (TextView) convertView.findViewById(R.id.room_name);
            viewholder.Gprice = (TextView) convertView.findViewById(R.id.type);
            convertView.setTag(viewholder);
        } else {
            convertView = view;
            viewholder = (ViewHolder) convertView.getTag();
        }
        if (i == 0) {
            viewholder.Gfirst.setText("*");
            viewholder.Gname.setText("商品");
            viewholder.Gprice.setText("价格");
        } else {
            viewholder.Gfirst.setText(list.get(i).getName());
            viewholder.Gname.setText(list.get(i).getName());
            viewholder.Gprice.setText(list.get(i).getPrice());
        }
        return convertView;
    }
}

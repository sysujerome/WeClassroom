package com.example.asus.hw5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

/*
 * Created by ASUS on 2017/10/22.
 */

public abstract class commonAdapter<buildingList> extends RecyclerView.Adapter<ReViewHolder>{
    private Context mContext;
    private List<buildingList> mlist;
    private int mLayoutId;
    private OnItemClickListener mOnItemClickListener;

    public commonAdapter(Context context, int layout, List<buildingList> datas) {
        mContext = context;
        mLayoutId = layout;
        mlist = datas;
    }

    @Override
    public ReViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ReViewHolder viewholder = ReViewHolder.get(mContext, parent, mLayoutId);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final ReViewHolder holder, int position) {
        convert(holder, mlist.get(position));
        if(mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    public void convert(ReViewHolder holder, buildingList buildingList) {
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void removeItem(int i) {
        mlist.remove(i);
        notifyDataSetChanged();
    }


}

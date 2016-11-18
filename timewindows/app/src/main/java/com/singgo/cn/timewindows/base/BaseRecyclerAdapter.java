package com.singgo.cn.timewindows.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.singgo.cn.timewindows.entity.BaseList;
import com.singgo.cn.timewindows.listener.BaseFragmentInterface;

import java.util.List;

/**
 * Created by hxz on 16/11/15 15:14.
 */
public abstract class BaseRecyclerAdapter <T extends BaseList> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected List<T> mData;

    public BaseRecyclerAdapter(){

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return bindView(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindData(holder,position);
    }

    protected abstract RecyclerView.ViewHolder bindView(ViewGroup parent,int viewType);

    protected abstract void bindData(RecyclerView.ViewHolder holder,int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }

}

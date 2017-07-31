package com.gg.tiantianshouyin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.model.tag.Tag;

import java.util.List;

/**
 * Created by tom on 2017/7/4.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {

    private Context mContext;

    private List<Tag> mTagList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView mCardView;

        TextView TagName;

        public ViewHolder(View view){
            super(view);
            mCardView = (CardView)view;
            TagName = (TextView)view.findViewById(R.id.tag_name);
        }
    }


    public TagAdapter(List<Tag> tagList){
        mTagList = tagList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext  == null){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("启动Album","");
                int position = holder.getAdapterPosition();
                Tag tag = mTagList.get(position);
                Intent intent = new Intent(mContext,AlbumActivity.class);
                intent.putExtra(DTransferConstants.TAG_NAME,tag.getTagName());
                mContext.startActivity(intent);
                Log.d("启动Album","");
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Tag tag = mTagList.get(position);
        holder.TagName.setText(tag.getTagName());
    }

    @Override
    public int getItemCount(){
        return mTagList.size();
    }

}

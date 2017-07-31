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

import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;


/**
 * Created by tom on 2017/7/4.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    private Context mContext;

    private List<Track> mTrackList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView mCardView;

        TextView TrackName;

        public ViewHolder(View view){
            super(view);
            mCardView = (CardView)view;
            TrackName = (TextView)view.findViewById(R.id.track_name);
        }
    }


    public TrackAdapter(List<Track> TrackList){
        mTrackList = TrackList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext  == null){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.track_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("跳转到播放界面了","klsdjfsljdf");
                int position = holder.getAdapterPosition();
                Log.d("位置",position+"");
                Track Track = mTrackList.get(position);
                Intent intent = new Intent(mContext,MainActivity.class);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Track Track = mTrackList.get(position);
        holder.TrackName.setText(Track.getTrackTitle());
    }

    @Override
    public int getItemCount(){
        return mTrackList.size();
    }

}
package com.gg.tiantianshouyin;

/**
 * Created by tom on 2017/7/4.
 */

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
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

/**
 * Created by tom on 2017/7/4.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private Context mContext;

    private List<Album> mAlbumList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView mCardView;

        TextView AlbumName;

        public ViewHolder(View view){
            super(view);
            mCardView = (CardView)view;
            AlbumName = (TextView)view.findViewById(R.id.album_name);
        }
    }


    public AlbumAdapter(List<Album> albumList){
        mAlbumList = albumList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext  == null){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.album_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Album album = mAlbumList.get(position);
                Intent intent = new Intent(mContext,TrackActivity.class);
                intent.putExtra(DTransferConstants.ALBUM_ID,album.getId());
                Log.d("获得专辑id",""+album.getId());
                mContext.startActivity(intent);
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Album album = mAlbumList.get(position);
        holder.AlbumName.setText(album.getAlbumTitle());
    }

    @Override
    public int getItemCount(){
        return mAlbumList.size();
    }

}
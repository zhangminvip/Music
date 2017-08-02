package com.gg.tiantianshouyin.launch_interface;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gg.tiantianshouyin.MyApplication;
import com.gg.tiantianshouyin.R;
import com.gg.tiantianshouyin.TagActivity;
import com.view.jameson.library.CardAdapterHelper;

import java.util.ArrayList;
import java.util.List;

import jameson.io.library.util.ToastUtils;


/**
 * Created by jameson on 8/30/16.
 */
class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<Integer> mList = new ArrayList<>();
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();

    public CardAdapter(List<Integer> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_item, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        holder.mImageView.setImageResource(mList.get(position));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.show(holder.mImageView.getContext(), "" + position);                    // Toast

                switch (position){
                    case 0:

                            String data_children = "children";
                            Intent intent_children = new Intent(MyApplication.getContext(), TagActivity.class);
                            intent_children.putExtra("category", data_children);
                            intent_children.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            MyApplication.getContext().startActivity(intent_children);
                            break;
                    case 1:
                        String data_humanity = "humanity";
                        Intent intent_humanity = new Intent(MyApplication.getContext(),TagActivity.class);
                        intent_humanity.putExtra("category",data_humanity);
                        intent_humanity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MyApplication.getContext().startActivity(intent_humanity);
                        break;

                    case 2:
                        String data_english = "english";
                        Intent intent_english = new Intent(MyApplication.getContext(),TagActivity.class);
                        intent_english.putExtra("category",data_english);
                        intent_english.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MyApplication.getContext().startActivity(intent_english);
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }

}

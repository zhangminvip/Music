package com.gg.tiantianshouyin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.AlbumList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 2017/7/4.
 */

public class AlbumActivity extends Activity {

    private static final String TAG = "AlbumActivity";
    private List<Album> mAlbumList = new ArrayList<>();

    private AlbumAdapter mAlbumAdapter;

    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "执行到onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_album);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_album);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);

        Intent i = getIntent();
        String data = i.getStringExtra(DTransferConstants.TAG_NAME);
        /**
         *
         * 获取专辑列表
         *
         */

        Map<String, String> albumDesc = new HashMap<String, String>();
        albumDesc.put(DTransferConstants.CATEGORY_ID, TagActivity.CATEGORY_ID);
        albumDesc.put(DTransferConstants.TAG_NAME, data);
        albumDesc.put(DTransferConstants.CALC_DIMENSION, "1");
        CommonRequest.getAlbumList(albumDesc, new IDataCallBack<AlbumList>() {
            @Override
            public void onSuccess(AlbumList list) {
                mAlbumList = list.getAlbums();
                Log.d(TAG, "*****************儿歌大全标签专辑列表**************");
                for (Album a : mAlbumList) {
                    Log.d(TAG, "AlbumTitle: " + a.getAlbumTitle() + ",Id: " + a.getId());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG,"执行线程");
                        mAlbumAdapter = new AlbumAdapter(mAlbumList);
                        recyclerView.setAdapter(mAlbumAdapter);
                        Log.d(TAG,"执行线程完成");
                    }
                });
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }


}

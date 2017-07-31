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
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 2017/7/4.
 */

public class TrackActivity extends Activity {


    private static String TAG = "TrackActivity";

    public static List<Track> mTrackList = new ArrayList<>();

    private TrackAdapter mTrackAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "执行到onCreate track");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_track);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_track);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        Log.d("打印Albumid","kk");
        long data = intent.getLongExtra(DTransferConstants.ALBUM_ID,0);
        Log.d("返回的序列号",data+"");

        Log.d("打印Albumid",String.valueOf(data));

        Map<String, String> trackDesc = new HashMap<String, String>();
        trackDesc.put(DTransferConstants.ALBUM_ID, String.valueOf(data));
        trackDesc.put(DTransferConstants.SORT, "asc");
        CommonRequest.getTracks(trackDesc, new IDataCallBack<TrackList>() {
            @Override
            public void onSuccess(TrackList list) {
                mTrackList = list.getTracks();
                Log.d(TAG, "*****************天天听儿歌声音列表**************");
                for (Track t : mTrackList) {
                    Log.d(TAG, t.getTrackTitle() + "  地方斯蒂芬" + t.getPlayUrlAmr());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG,"执行线程");
                        mTrackAdapter = new TrackAdapter(mTrackList);
                        recyclerView.setAdapter(mTrackAdapter);
                        Log.d(TAG,"执行线程完成");

                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                Log.d(TAG,"执行线程发生错误");
            }
        });
    }
}

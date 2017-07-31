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
import com.ximalaya.ting.android.opensdk.model.track.SearchTrackList;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 2017/7/6.
 */

public class SearchActivity extends Activity {
    public  static final String APP_SECRET = "2d5e40f87904d6cb292b6388e82680a7";
    private static String TAG = "SearchActivity";

    private List<Track> mTrackList = new ArrayList<>();

    private SearchAdapter mSearchAdapter;
    private RecyclerView recyclerView;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);
        CommonRequest.getInstanse().init(this, APP_SECRET);
        initView();
        initEvent();


    }

    public void initView(){
         recyclerView = (RecyclerView)findViewById(R.id.recycler_view_search);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void initEvent(){

        Intent intent = getIntent();
        String music_name = intent.getStringExtra("music_name");
        Log.d(TAG,"获取到传来的musicname"+music_name);
        Map<String,String> map = new HashMap<String,String>();
        map.put(DTransferConstants.SEARCH_KEY,music_name);
        map.put(DTransferConstants.CATEGORY_ID,"2");
        map.put(DTransferConstants.PAGE,"10");
        CommonRequest.getSearchedTracks(map, new IDataCallBack<SearchTrackList>() {
            @Override
            public void onSuccess(SearchTrackList searchTrackList) {
                mTrackList = searchTrackList.getTracks();
                mSearchAdapter = new SearchAdapter(mTrackList);
                recyclerView.setAdapter(mSearchAdapter);
                Log.d(TAG,"执行线程完成");


            }

            @Override
            public void onError(int i, String s) {

                Log.d(TAG,"显示搜索结果出现错误");
            }
        });

    }



}

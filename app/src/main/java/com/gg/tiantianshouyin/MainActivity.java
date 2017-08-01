package com.gg.tiantianshouyin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gg.tiantianshouyin.function.FastBlurUtil;
import com.gg.tiantianshouyin.launch_interface.BlurBitmapUtils;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.tag.Tag;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ximalaya.ting.android.opensdk.player.constants.PlayerConstants.STATE_PAUSED;
import static com.ximalaya.ting.android.opensdk.player.constants.PlayerConstants.STATE_STARTED;

public class MainActivity extends Activity {

    private Button btn_pause;
    private Button btn_play;
    private Button btn_next;
    private Button btn_previous;

    private TextView title;

    private int data;

    private static final String TAG = "zhangmin";
    private static final String APP_SECRET = "2d5e40f87904d6cb292b6388e82680a7";

    public List<Category> mCategoryList = new ArrayList<Category>();      //类别列表  有声书 、音乐、娱乐。。。。。
    public List<Tag> mTagList = new ArrayList<Tag>();                      //类别下的标签列表    悬疑、 言情  幻想  等
    public List<Album> mAlbumList = new ArrayList<Album>();              //标签后专辑
    public List<Track> mTrackList = new ArrayList<Track>();               //专辑下的声音

    private RelativeLayout layout;    //为毛玻璃准备


    private XmPlayerManager mPlayerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        // 模糊背景
        layout = (RelativeLayout)this.findViewById(R.id.mainactivity);
        setBackground(R.drawable.background);


        mPlayerManager = XmPlayerManager.getInstance(MainActivity.this);
        mPlayerManager.init();

        Intent intent = getIntent();
        data = intent.getIntExtra("position",0);
        initView();
        Log.d("","标题栏"+TrackActivity.mTrackList.get(data).getTrackTitle()+"data:" +data);
        title.requestFocus();
        title.setText(TrackActivity.mTrackList.get(data).getTrackTitle());
        title.requestFocus();
        Log.d("","标题栏"+title.isFocused());
        mPlayerManager.playList(TrackActivity.mTrackList,data);
        mPlayerManager.play();
        checkPlayerStatus();
        initEvent();
    }

    /**
     * 设置毛玻璃背景
     */
    @SuppressWarnings("deprecation")
    private void setBackground(int id){


        Bitmap originbmp = null;

         originbmp = BitmapFactory.decodeResource(getResources(),id);

        Bitmap thumbnailBitmap = Bitmap.createScaledBitmap(originbmp,originbmp.getWidth()/10,originbmp.getHeight()/10,false);

        final Bitmap blurBmp = FastBlurUtil.doBlur(thumbnailBitmap,8,false);
        final Drawable newBitmapDrawable = new BitmapDrawable(blurBmp);

        layout.post(new Runnable() {
            @Override
            public void run() {
                layout.setBackground(newBitmapDrawable);
            }
        });
    }




    /**
     * 初始化事件,设置播放器监听器。
     */
    public void initEvent() {

        mPlayerManager.addPlayerStatusListener(new IXmPlayerStatusListener() {
            @Override
            public void onPlayStart() {

            }

            @Override
            public void onPlayPause() {

            }

            @Override
            public void onPlayStop() {

            }

            @Override
            public void onSoundPlayComplete() {

            }

            @Override
            public void onSoundPrepared() {

            }

            @Override
            public void onSoundSwitch(PlayableModel playableModel, PlayableModel playableModel1) {

            }

            @Override
            public void onBufferingStart() {

            }

            @Override
            public void onBufferingStop() {

            }

            @Override
            public void onBufferProgress(int i) {

            }

            @Override
            public void onPlayProgress(int i, int i1) {

            }

            @Override
            public boolean onError(XmPlayerException e) {
                return false;
            }
        });
    }



    public void initView() {
        btn_pause = (Button) findViewById(R.id.pause);
        btn_play = (Button) findViewById(R.id.play);
        title = (TextView)findViewById(R.id.music_title);
        btn_next = (Button)findViewById(R.id.next);
        btn_previous = (Button)findViewById(R.id.previous);
    }


    /**
     * 检查播放器状态,来改变状态显示图标。
     */
    public void checkPlayerStatus() {
        if (mPlayerManager.getPlayerStatus() == STATE_STARTED) {
            btn_play.setVisibility(View.INVISIBLE);
            btn_pause.setVisibility(View.VISIBLE);

            Log.d("Main", "" + mPlayerManager.getPlayerStatus());
        } else if (mPlayerManager.getPlayerStatus() == STATE_PAUSED) {
            btn_play.setVisibility(View.VISIBLE);
            btn_pause.setVisibility(View.INVISIBLE);
            Log.d("Main", "" + mPlayerManager.getPlayerStatus());
        }
    }



    public void play(View view) {
        Log.d(TAG, "play");
        btn_pause.setVisibility(View.VISIBLE);
        btn_play.setVisibility(View.INVISIBLE);
        btn_next.setVisibility(View.VISIBLE);
        btn_previous.setVisibility(View.VISIBLE);
        mPlayerManager.playList(TrackActivity.mTrackList, data);
        mPlayerManager.play();

    }

    public void stop(View view) {
        Log.d(TAG, "stop");
        mPlayerManager.stop();
    }

    public void pause(View view) {

        btn_pause.setVisibility(View.INVISIBLE);
        btn_play.setVisibility(View.VISIBLE);
        Log.d(TAG, "pause");
        mPlayerManager.pause();

    }

    public void next(View view) {
        Log.d(TAG, "next");
        mPlayerManager.playNext();
        title.setText(TrackActivity.mTrackList.get(mPlayerManager.getCurrentIndex()).getTrackTitle());
        title.requestFocus();
        checkPlayerStatus();
    }

    public void preview(View view) {
        Log.d(TAG, "preview");
        mPlayerManager.playPre();
        title.setText(TrackActivity.mTrackList.get(mPlayerManager.getCurrentIndex()).getTrackTitle());
        title.requestFocus();
        checkPlayerStatus();
    }


    @Override
    public void onPause(){
        mPlayerManager.stop();

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mPlayerManager.release();
        super.onDestroy();
    }
}

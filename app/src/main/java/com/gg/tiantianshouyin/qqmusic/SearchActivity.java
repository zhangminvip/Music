package com.gg.tiantianshouyin.qqmusic;

/**
 * Created by tom on 2017/7/18.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gg.tiantianshouyin.R;
import com.gg.tiantianshouyin.function.FastBlurUtil;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SearchActivity  extends Activity implements View.OnClickListener {
    private String music_name = "宠爱";
    private Button play;
    private Button pause;
    private Button preview;
    private Button next;
    private TextView title;
    private LinearLayout layout;

    private MediaPlayer mp = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_qq);


        // 模糊背景
        layout = (LinearLayout)this.findViewById(R.id.mainactivity_qq);
        setBackground(R.drawable.background);




        Intent intent = getIntent();
        music_name = intent.getStringExtra("music_name");

        initView();
        title.setText(music_name);
        title.requestFocus();
        initEvent();
        searchSong();
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



    private void initView() {
        play = (Button) findViewById(R.id.play_qq);
        pause = (Button) findViewById(R.id.pause_qq);
//        next = (Button) findViewById(R.id.next_qq);
//        preview = (Button) findViewById(R.id.previous_qq);
        title = (TextView)findViewById(R.id.music_title_qq);
    }

    private void initEvent() {
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
//        next.setOnClickListener(this);
//        preview.setOnClickListener(this);

    }

    /**
     * 搜索歌曲
     */
    private void searchSong() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://s.music.qq.com/fcgi-bin/music_search_new_platform?t=0&n=1&aggr=1&cr=1&loginUin=0&format=json&inCharset=GB2312&outCharset=utf-8&notice=0&platform=jqminiframe.json&needNewCode=0&p=1&catZhida=0&remoteplace=sizer.newclient.next_song&w=" + music_name)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        remind("正在为您播放歌曲" + music_name);
                        String responseData = response.body().string();
                        // 播放歌曲
                        mp.setDataSource("http://ws.stream.qqmusic.qq.com/" + getSongId(responseData) + ".m4a?fromtag=46");
                        mp.prepare();
                        mp.start();
                    } else {
                        remind("播放失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 在子线程中发起Toast提醒
     * @param msg 提醒内容
     */
    private void remind(final String msg) {
        runOnUiThread(new Thread(){
            @Override
            public void run() {
                Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 获取歌曲的id
     *
     * @param jsonData
     * @return
     */
    private String getSongId(String jsonData) {
        Gson gson = new Gson();
        SearchResult search = gson.fromJson(jsonData, SearchResult.class);
        String[] params = search.getData().getSong().getList().get(0).getF().split("\\|");
//        for (String s : params) {
//            Log.d("json", s);
//        }
        String id = params[0];
        Log.d("json", params[1] + "id: " + id);
        return id;
    }


    int i = 0;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.play_qq:
                mp.start();
                checkPlayerStatus();
                break;
            case R.id.pause_qq:
                i = 1;
                mp.pause();
                checkPlayerStatus();
                break;
            default:
                break;
        }

    }

    public void checkPlayerStatus() {
        if (mp.isPlaying() == true) {
            play.setVisibility(View.INVISIBLE);
            pause.setVisibility(View.VISIBLE);

            Log.d("Main", "");
        } else if (mp.isPlaying() == false) {
            play.setVisibility(View.VISIBLE);
            pause.setVisibility(View.INVISIBLE);
            Log.d("Main", "");
        }
    }

    @Override
    protected void onRestart() {


        Intent intent = getIntent();
        music_name = intent.getStringExtra("music_name");



        title.setText(music_name);
        title.requestFocus();

        searchSong();

        Log.d("生命周期","onRestart");
        Log.d("生命周期","onRestart:: music_name:" +music_name);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("生命周期","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("生命周期","onResume");
    }

    @Override
    protected void onStop(){

        super.onStop();
        Log.d("生命周期","onStop");
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        finish();    // 在此处调用onDestroy并不能finish掉activity，或许onDestroy只是一个回调，处理一些返回键的事情，点桌面键必须手动finish()....

    }

    protected void onDestroy() {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        super.onDestroy();
        Log.d("生命周期","onDestory");
    }
}

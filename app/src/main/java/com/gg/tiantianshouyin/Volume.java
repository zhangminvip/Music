package com.gg.tiantianshouyin;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * Created by tom on 2017/7/18.
 */

public class Volume extends LinearLayout {
    private int maxVolume, currentVolum;

    private AudioManager mAudioManager;

    private SeekBar mView_sb_play_volum = null;

    public Volume(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.volume_seek_bar,this);

        mView_sb_play_volum = (SeekBar)findViewById(R.id.seekbar_def);
        mAudioManager = (AudioManager)MyApplication.getContext().getSystemService(Context.AUDIO_SERVICE);
        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mView_sb_play_volum.setMax(maxVolume);
        currentVolum = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mView_sb_play_volum.setProgress(currentVolum);

        mView_sb_play_volum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

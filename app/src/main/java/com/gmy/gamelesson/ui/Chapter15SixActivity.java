package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import com.gmy.gamelesson.R;
import com.gmy.gamelesson.surfaceview.Chapter15.MySurfaceView6;

import java.util.HashMap;

public class Chapter15SixActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    MySurfaceView6 mGLSurfaceView;
    SoundPool soundPool;//声音池
    HashMap<Integer, Integer> soundPoolMap; //声音池中声音ID与自定义声音ID的Map
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new MySurfaceView6(this);
        mGLSurfaceView.requestFocus();//获取焦点
        mGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控
        initSound();
        setContentView(mGLSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
    public void initSound()
    {
        //声音池
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();

        //炮弹爆炸音效
        soundPoolMap.put(1, soundPool.load(this, R.raw.explode, 1));
    }

    public void playSound(int sound, int loop)
    {
        AudioManager mgr = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent / streamVolumeMax;

        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
    }
}

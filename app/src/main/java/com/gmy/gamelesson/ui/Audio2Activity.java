package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gmy.gamelesson.R;

public class Audio2Activity extends AppCompatActivity {

    /** Called when the activity is first created. */
    private Button bPlay;					//播放按钮
    private Button bPause;					//暂停按钮
    private Button bStop;					//停止按钮
    private Button bAdd;					//增大音量
    private Button bReduce;					//降低音量
    private boolean pauseFlag=false;		//暂停标记，true暂停态，false非暂停态
    MediaPlayer mp;							//MediaPlayer引用
    AudioManager am;						//AudioManager引用
    Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {	//Activity创建时调用
        super.onCreate(savedInstanceState);
        this.mContext = this;
        setContentView(R.layout.activity_audio2);					//设置Activity的显示内容
        bPlay=(Button)findViewById(R.id.ButtonPlay);	//播放按钮的实例化
        bPause=(Button)findViewById(R.id.ButtonPause);	//暂停按钮的实例化
        bStop=(Button)findViewById(R.id.ButtonStop);	//停止按钮的实例化
        bAdd=(Button)findViewById(R.id.ButtonVAdd);		//增大音量按钮的实例化
        bReduce	=(Button)findViewById(R.id.ButtonVReduce);//降低音量按钮的实例化
        mp=new MediaPlayer();
        am=(AudioManager) this.getSystemService(this.AUDIO_SERVICE);
        bPlay.setOnClickListener(new View.OnClickListener() {//播放按钮的监听器
            @Override
            public void onClick(View v) {
                try{
                    /*
                    法1. 直接调用create函数实例化一个MediaPlayer对象，播放位于res/raw/test.mp3文件
MediaPlayer  mMediaPlayer = MediaPlayer.create(this, R.raw.test);

法2. test.mp3放在res/raw/目录下，使用setDataSource(Context context, Uri uri)
mp = new MediaPlayer();
Uri setDataSourceuri = Uri.parse("android.resource://com.android.sim/"+R.raw.test);
mp.setDataSource(this, uri);

说明：此种方法是通过res转换成uri然后调用setDataSource()方法，需要注意格式Uri.parse("android.resource://[应用程序包名Application package name]/"+R.raw.播放文件名);
例子中的包名为com.android.sim，播放文件名为：test;特别注意包名后的"/"。

法3. test.mp3文件放在assets目录下，使用setDataSource(FileDescriptor fd, long offset, long length)
AssetManager assetMg = this.getApplicationContext().getAssets();
AssetFileDescriptor fileDescriptor = assetMg.openFd("test.mp3");
mp.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                     */
                    AssetManager assetMg = mContext.getApplicationContext().getAssets();
                    AssetFileDescriptor fileDescriptor = assetMg.openFd("kanonga.mp3");
                    mp.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                   // mp.setDataSource("/sdcard/dl.mid");		//加载音频，进入Initialized状态。
                }catch(Exception e){e.printStackTrace();}
                try{
                    mp.prepare();							//进入prepared状态。

                }catch(Exception e){e.printStackTrace();}
                mp.start();										//播放音乐
                Toast.makeText(Audio2Activity.this, "播放音乐", Toast.LENGTH_SHORT).show();
            }});
        bPause.setOnClickListener(new View.OnClickListener() {	//暂停按钮添加监听器
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){								//如果是在播放态
                    mp.pause();									//调用暂停方法
                    pauseFlag=true;								//设置暂停标记
                }else if(pauseFlag){
                    mp.start();									//播放音乐
                    pauseFlag=false;							//设置暂停标记
                    Toast.makeText(Audio2Activity.this, "暂停播放", Toast.LENGTH_SHORT).show();
                }}
        });
        bStop.setOnClickListener(new View.OnClickListener() {	//停止按钮添加监听器
            @Override
            public void onClick(View v) {
                mp.stop();										//停止播放
                mp.reset();									//重置状态到uninitialized态
                try{
                    mp.setDataSource("/sdcard/dl.mid");		//加载音频，进入Initialized状态。
                }catch(Exception e){e.printStackTrace();}
                try{
                    mp.prepare();							//进入prepared状态。
                }catch(Exception e){e.printStackTrace();}
                Toast.makeText(Audio2Activity.this, "停止播放", Toast.LENGTH_SHORT).show();
            }});
        bAdd.setOnClickListener(new View.OnClickListener() {	//音量增大按钮添加监听器
            @Override
            public void onClick(View v) {
                am.adjustVolume(AudioManager.ADJUST_RAISE, 0);		//增大音量
                System.out.println("faaa");
                Toast.makeText(Audio2Activity.this, "增大音量", Toast.LENGTH_SHORT).show();
            }});
        bReduce.setOnClickListener(new View.OnClickListener() {	//音量降低按钮添加监听器
            @Override
            public void onClick(View v) {
                am.adjustVolume(AudioManager.ADJUST_LOWER, 0);		//减小音量
                Toast.makeText(Audio2Activity.this, "减小音量", Toast.LENGTH_SHORT).show();
            }});
    }
}

package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gmy.gamelesson.surfaceview.Ball4SurfaceView;

public class Ball4SurfaceActivity extends AppCompatActivity {

    Ball4SurfaceView msv;						//MySurfaceView引用
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msv=new Ball4SurfaceView(this);		//实例化MySurfaceView对象
        setContentView(msv);				//设置Activity内容
    }
}

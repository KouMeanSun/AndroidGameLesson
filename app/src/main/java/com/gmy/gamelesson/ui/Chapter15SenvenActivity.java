package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.gmy.gamelesson.surfaceview.Chapter15.MySurfaceView7;

public class Chapter15SenvenActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    MySurfaceView7 mGLSurfaceView;
    public static float screenWidth;//屏幕宽度
    public static float screenHeight;//屏幕高度
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth=dm.widthPixels;			//dm.widthPixels    获取屏幕横向分辨率
        screenHeight=dm.heightPixels;		//dm.heightPixels	获取屏幕竖向分辨率
        mGLSurfaceView = new MySurfaceView7(this);
        mGLSurfaceView.requestFocus();//获取焦点
        mGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控
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
}

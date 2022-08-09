package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gmy.gamelesson.surfaceview.Cube2SurfaceView;

public class Cube2Activity extends AppCompatActivity {

    private Cube2SurfaceView mGLSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLSurfaceView = new Cube2SurfaceView(this);
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

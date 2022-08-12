package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gmy.gamelesson.surfaceview.Chapter15.MySurfaceView;

public class Chapter15Activity extends AppCompatActivity {
    /** Called when the activity is first created. */
    MySurfaceView mySurfaceView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mySurfaceView=new MySurfaceView(this);
        mySurfaceView.requestFocus();
        mySurfaceView.setFocusableInTouchMode(true);
        setContentView(mySurfaceView);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        mySurfaceView.onResume();
    }
    @Override
    public void onPause()
    {
        super.onPause();
        mySurfaceView.onPause();
    }
}

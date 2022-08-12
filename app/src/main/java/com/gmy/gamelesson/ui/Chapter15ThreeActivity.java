package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gmy.gamelesson.surfaceview.Chapter15.MySurfaceView3;

public class Chapter15ThreeActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    MySurfaceView3 mySurfaceView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mySurfaceView=new MySurfaceView3(this);
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

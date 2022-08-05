package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.gmy.gamelesson.R;
import com.gmy.gamelesson.surfaceview.MySurfaceView;

public class SurfaceActivity extends AppCompatActivity {
    private MySurfaceView msv;			//得到surfaceView的引用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        msv=new MySurfaceView(SurfaceActivity.this);			//实例化MySurfaceView的对象
//        requestWindowFeature(Window.FEATURE_NO_TITLE);	//设置屏幕显示没有title栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);	//设置全屏
        //设置只允许横屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(msv);							//设置Activity显示的内容为msv
    }
}

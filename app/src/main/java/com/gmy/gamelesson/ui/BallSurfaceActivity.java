
package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.gmy.gamelesson.R;
import com.gmy.gamelesson.surfaceview.BallSurfaceView;

public class BallSurfaceActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    BallSurfaceView msv;
    ToggleButton tb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msv=new BallSurfaceView(this);				//实例化MySurfaceView
        setContentView(R.layout.activity_ball_surface);				//设置Acitivity的内容
        msv.requestFocus();							//获取焦点
        msv.setFocusableInTouchMode(true);			//设置为可触控
        LinearLayout lla=(LinearLayout)findViewById(R.id.ball_lla);
        lla.addView(msv);							//将SurfaceView加入LinearLayout中
        tb=(ToggleButton)findViewById(R.id.ToggleButton01);//添加监听器
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                msv.openLightFlag=!msv.openLightFlag;
            }});}
    @Override
    protected void onPause() {					//当另一个Acitvity遮挡着当前的Activity时调用
        super.onPause();
        msv.onPause();
    }
    @Override
    protected void onResume() {					//当Acitvity获得用户焦点时调用
        super.onResume();
        msv.onResume();
    }
}

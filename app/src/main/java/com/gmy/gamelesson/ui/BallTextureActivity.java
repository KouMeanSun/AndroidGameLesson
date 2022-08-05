package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.gmy.gamelesson.R;
import com.gmy.gamelesson.surfaceview.BallTextureSurfaceView;

public class BallTextureActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    BallTextureSurfaceView mGLSurfaceView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_texture);
        mGLSurfaceView = new BallTextureSurfaceView(this);
        mGLSurfaceView.requestFocus();//获取焦点
        mGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控

        LinearLayout ll=(LinearLayout)findViewById(R.id.balltexture_main_liner);
        ll.addView(mGLSurfaceView);

        ToggleButton tb=(ToggleButton)this.findViewById(R.id.ToggleButton01);
        tb.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
                    {
                        mGLSurfaceView.setSmoothFlag(!mGLSurfaceView.isSmoothFlag());
                    }
                }
        );
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

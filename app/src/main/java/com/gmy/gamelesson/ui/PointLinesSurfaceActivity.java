package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.gmy.gamelesson.R;
import com.gmy.gamelesson.surfaceview.PointsAndLinesSurfaceView;

public class PointLinesSurfaceActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    private PointsAndLinesSurfaceView mSurfaceView;//声明MySurfaceView对象
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_lines_surface);
        mSurfaceView=new PointsAndLinesSurfaceView(this);//创建MySurfaceView对象
        LinearLayout ll=(LinearLayout)this.findViewById(R.id.point_lines_activity_linear);//获得线性布局的引用
        ll.addView(mSurfaceView);//将MySurfaceView对象放入线性布局中
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mSurfaceView.onPause();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mSurfaceView.onResume();
    }
}

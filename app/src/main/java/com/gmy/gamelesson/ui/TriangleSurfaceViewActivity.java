package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.gmy.gamelesson.R;
import com.gmy.gamelesson.surfaceview.TriangleSurfaceView;

public class TriangleSurfaceViewActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    private TriangleSurfaceView mSurfaceView;//声明MySurfaceView对象
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle_surface_view);
        mSurfaceView=new TriangleSurfaceView(this);//创建MySurfaceView对象
        mSurfaceView.requestFocus();//获取焦点
        mSurfaceView.setFocusableInTouchMode(true);//设置为可触控
        LinearLayout ll=(LinearLayout)this.findViewById(R.id.activity_triangle_surfaceview_liner);//获得线性布局的引用
        ll.addView(mSurfaceView);
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

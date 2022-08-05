package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.gmy.gamelesson.R;
import com.gmy.gamelesson.surfaceview.HexagonSurfaceView;

public class HexagonActivity extends AppCompatActivity {
    private HexagonSurfaceView mSurfaceView;			//声明MySurfaceView对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexagon);			//设置布局
        mSurfaceView = new HexagonSurfaceView(this);	//创建
        mSurfaceView.requestFocus();//获取焦点	//MySurfaceView对象
        mSurfaceView.setFocusableInTouchMode(true);//设置为可触控
        LinearLayout ll=(LinearLayout)findViewById(R.id.hexagon_main_liner); //获得布局引用
        ll.addView(mSurfaceView);//在布局中添加MySurfaceView对象
        //控制是否打开背面剪裁的ToggleButton
        ToggleButton tb=(ToggleButton)this.findViewById(R.id.ToggleButton01);//获得按钮引用
        tb.setOnCheckedChangeListener(new MyListener());        //为按钮设置监听器
    }
    class MyListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // TODO Auto-generated method stub
            mSurfaceView.isPerspective=!mSurfaceView.isPerspective;//在正交投影与透视投影之间切换
            mSurfaceView.requestRender();//重新绘制
        }
    }
    @Override
    protected void onResume() {	//
        super.onResume();		//
        mSurfaceView.onResume();//
    }
    @Override
    protected void onPause() {	//
        super.onPause();		//
        mSurfaceView.onPause();	//
    }
}

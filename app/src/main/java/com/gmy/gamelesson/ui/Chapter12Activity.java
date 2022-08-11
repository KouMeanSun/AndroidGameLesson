package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;
import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.gmy.gamelesson.R;

public class Chapter12Activity extends AppCompatActivity {

    SensorManager mySensorManager;	//SensorManager对象引用

    //SensorManagerSimulator mySensorManager;		//使用SensorSimulator模拟器时声明SensorSensorManager对象引用的方法

    TextView tvX;	//TextView对象引用
    TextView tvY;	//TextView对象引用
    TextView tvZ;	//TextView对象引用
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter12);
        tvX = (TextView)findViewById(R.id.tvX);
        tvY = (TextView)findViewById(R.id.tvY);
        tvZ = (TextView)findViewById(R.id.tvZ);

        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);	//获得SensorManager对象

        //mySensorManager = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);//使用SensorSimulator模拟时声明SensorSensorManager对象引用的方法
        //mySensorManager.connectSimulator();
    }
    @Override
    protected void onResume() {						//重写onResume方法
        mySensorManager.registerListener(			//注册监听器
                mySensorListener, 					//监听器对象
                SensorManager.SENSOR_ORIENTATION,	//传感器类型
                SensorManager.SENSOR_DELAY_UI		//传感器事件传递的频度
        );
        super.onResume();
    }
    @Override
    protected void onPause() {									//重写onPause方法
        mySensorManager.unregisterListener(mySensorListener);	//取消注册监听器
        super.onPause();
    }
    //开发实现了SensorEventListener接口的传感器监听器
    private SensorListener mySensorListener = new SensorListener(){
        public void onAccuracyChanged(int sensor, int accuracy)
        {
        }
        public void onSensorChanged(int sensor, float[] values)
        {
            if(sensor == SensorManager.SENSOR_ORIENTATION)
            {//判断是否为加速度传感器变化产生的数据
                //将提取的数据显示到TextView
                tvX.setText("Pitch轴上的旋转角度为："+values[0]);
                tvY.setText("Yaw轴上的旋转角度为："+values[1]);
                tvZ.setText("Roll轴上的旋转角度为："+values[2]);
            }
        }
    };
}

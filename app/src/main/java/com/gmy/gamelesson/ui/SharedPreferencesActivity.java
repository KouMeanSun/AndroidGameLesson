package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.gmy.gamelesson.R;

import java.util.Date;

public class SharedPreferencesActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        SharedPreferences sp=this.getSharedPreferences("sharePre", Context.MODE_PRIVATE);
        //返回一个SharedPreferences实例，第一个参数是Preferences名字，第二个参数是使用默认的操作
        String lastLogin=sp.getString(				//从SharedPreferences中读取上次访问的时间
                "ll",								//键值
                null								//默认值
        );
        if(lastLogin==null){
            lastLogin="欢迎您，您是第一次访问本Preferences";
        }else{
            lastLogin="欢迎回来，您上次于"+lastLogin+"登录";
        }
        //向SharedPreferences中写回本次访问时间
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("ll", new Date().toLocaleString());	//向editor中放入现在的时间
        editor.commit();										//提交editor
        tv=(TextView)this.findViewById(R.id.TextView01);
        tv.setText(lastLogin);
    }
}

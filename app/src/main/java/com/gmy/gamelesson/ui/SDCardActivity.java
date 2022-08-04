package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmy.gamelesson.R;

import java.io.File;
import java.io.FileInputStream;

public class SDCardActivity extends AppCompatActivity {
    private static final String TAG = "SDCardActivity";
    /** Called when the activity is first created. */
    Button but;				//打开按钮引用
    private String androidSummaryFullPath = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);
        Intent intent = getIntent();
        if (null != intent){
            this.androidSummaryFullPath = intent.getStringExtra("androidSummaryFullPath");
        }
        but=(Button)findViewById(R.id.Button01);
        //打开按钮初始化
        but.setOnClickListener(new View.OnClickListener() {
            //为打开按钮添加监听器
            @Override
            public void onClick(View v) {
//                String contentResult=loadContentFromSDCard("AndroidSummary.txt");
                String contentResult=loadContentFromSDCard(androidSummaryFullPath);
                //调用读取文件方法，获得文件内容
                EditText etContent=(EditText)findViewById(R.id.EditText01);
                //实例化EditText
                etContent.setText(contentResult);
                //设置EditText的内容
            }
        });
    }
    public String loadContentFromSDCard(String fileName){
        //从SD卡读取内容
        String content=null;		//sd卡 的内容字符串
        try{
            File f=new File(fileName);//待读取的文件
            int length=(int)f.length();
            byte[] buff=new byte[length];
            FileInputStream fis=new FileInputStream(f);
            fis.read(buff);	// 从此输入流中将 byte.length 个字节的数据读入一个 byte 数组中
            fis.close();	//关闭此输入流并释放与此流关联的所有系统资源
            content=new String(buff,"UTF-8");
        }catch(Exception e){
            Log.e(TAG,"error:"+e.getLocalizedMessage());
            Toast.makeText(this, "对不起，没有找到文件",
                    Toast.LENGTH_SHORT).show();
        }
        return content;
    }
}

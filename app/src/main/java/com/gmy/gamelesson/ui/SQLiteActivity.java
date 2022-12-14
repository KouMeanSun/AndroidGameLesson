package com.gmy.gamelesson.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gmy.gamelesson.R;
import com.gmy.gamelesson.sqlitedb.SqLiteDBHelper;

public class SQLiteActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    private Button createButton;		//创建数据库按钮
    private Button insertBut;			//增加数据库记录按钮
    private Button updateBut;			//更新数据库记录按钮
    private Button queryBut;			//查询数据库记录按钮
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        createButton=(Button)findViewById(R.id.ButtonCreate);	//实例化创建数据库按钮
        insertBut=(Button)findViewById(R.id.ButtonInsert);		//实例化插入数据库按钮
        updateBut=(Button)findViewById(R.id.ButtonUpdate);		//实例化更新数据库按钮
        queryBut=(Button)findViewById(R.id.ButtonQuery);		//实例化查询数据库按钮
        createButton.setOnClickListener(new View.OnClickListener() {//创建数据库时调用
            @Override
            public void onClick(View v) {
                SqLiteDBHelper dh=new SqLiteDBHelper(SQLiteActivity.this,"testdb",null,1);//创建数据库
                System.out.println("create or open database success!");
                SQLiteDatabase sld=dh.getReadableDatabase();
                //得到一个SQLiteDatabase对象，用于操控数据库
                Toast.makeText(SQLiteActivity.this,	 "创建或打开数据库", Toast.LENGTH_SHORT).show();
            }});
        insertBut.setOnClickListener(new View.OnClickListener() {//增加数据库记录时调用
            @Override
            public void onClick(View v) {
                ContentValues cv=new ContentValues();
                //得到ContentValues对象
                cv.put("uid", 1);
                //放入键值对，键要与列名一致，值要与列的数据类型一致
                cv.put("uname", "zcl");
                //放入键值对，键要与列名一致，值要与列的数据类型一致
                SqLiteDBHelper dh=new SqLiteDBHelper(SQLiteActivity.this,"testdb",null,1);//创建数据库
                Toast.makeText(SQLiteActivity.this,	 "插入记录", Toast.LENGTH_SHORT).show();
                SQLiteDatabase sld=dh.getWritableDatabase();
                //得到一个SQLiteDatabase对象，用于操控数据库
                sld.insert("sqlitetest", null, cv);//增加数据库记录
                System.out.println("success insert a new content!");
            }});
        updateBut.setOnClickListener(new View.OnClickListener() {
            //更新数据库记录时调用
            @Override
            public void onClick(View v) {
                SqLiteDBHelper dh=new SqLiteDBHelper(SQLiteActivity.this,"testdb",null,1);
                //创建数据库
                SQLiteDatabase sld=dh.getWritableDatabase();
                //得到一个SQLiteDatabase对象，用于操控数据库
                ContentValues cv = new ContentValues();	//得到ContentValues对象
                Toast.makeText(SQLiteActivity.this,	 "更新记录", Toast.LENGTH_SHORT).show();
                cv.put("uname", "zcl_update");
                sld.update("sqlitetest", cv, "uid=?", new String[]{"1"});//更新数据库记录
                System.out.println("success updata the content!");
            }});
        queryBut.setOnClickListener(new View.OnClickListener() {
            //查询数据库记录时调用
            @Override
            public void onClick(View v) {
                SqLiteDBHelper dh=new SqLiteDBHelper(SQLiteActivity.this,"testdb",null,1);//创建数据库
                Toast.makeText(SQLiteActivity.this,	 "查询记录", Toast.LENGTH_SHORT).show();
                SQLiteDatabase sld=dh.getReadableDatabase();
                //得到一个SQLiteDatabase对象，用于操控数据库
                Cursor cursor=sld.query("sqlitetest", new String[]{"uid","uname"}, "uid=?",
                        new String[]{"1"}, null, null, null);
                while(cursor.moveToNext()){				//打印输出
                    String name=cursor.getString(cursor.getColumnIndex("uname"));
                    System.out.println("query result:"+name);
                }}});
    }
}

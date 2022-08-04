package com.gmy.gamelesson.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqLiteDBHelper extends SQLiteOpenHelper {
    public SqLiteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {//继承SQLiteOpenHelper的类，必须有该构造函数
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库时调用,此方法是在调用了getReadableDatabase()或getWritableDatabase()后才调用
        db.execSQL("create table sqlitetest(uid long,uname varchar(25))");
        System.out.println("already create a database:sqlitetest.");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级数据库时掉用
    }
}

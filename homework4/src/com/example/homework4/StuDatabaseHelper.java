package com.example.homework4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StuDatabaseHelper extends SQLiteOpenHelper{
	
	String createSql="create table student(_id integer primary key autoincrement,sno varchar(50),sname varchar(50),major varchar(100))";

	public StuDatabaseHelper(Context context, String name,int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createSql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}

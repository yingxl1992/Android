package com.example.easycount.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CountDatabaseHelper extends SQLiteOpenHelper{
	String createExpendSql="create table expend(_id integer primary key autoincrement,type integer,addTime varchar(50),money float,des varchar(100),accountType integer,addyear integer,addmonth,addday integer)";
	String createIncomeSql="create table income(_id integer primary key autoincrement,type integer,addTime varchar(50),money float,des varchar(100),accountType integer,addyear integer,addmonth,addday integer)";
	
	public CountDatabaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createExpendSql);	
		db.execSQL(createIncomeSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}

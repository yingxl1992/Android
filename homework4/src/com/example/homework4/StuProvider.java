package com.example.homework4;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class StuProvider extends ContentProvider{
	
	SQLiteDatabase db;
	StuDatabaseHelper dbHelper;
	
	@Override
	public boolean onCreate() { 
		dbHelper=new StuDatabaseHelper(this.getContext(),"homework4.db3",1);
		db=dbHelper.getReadableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cursor=db.query("student", null, selection, selectionArgs, null, null, null);
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}

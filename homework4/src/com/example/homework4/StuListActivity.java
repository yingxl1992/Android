package com.example.homework4;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class StuListActivity extends Activity{
	
	SQLiteDatabase db;
	ListView listView;
	StuDatabaseHelper dbHelper;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stulist);
		
		listView=(ListView)findViewById(R.id.listView1);
		
		dbHelper=new StuDatabaseHelper(this,"homework4.db3",1);		
		db=dbHelper.getReadableDatabase();
		
		Cursor cursor=db.rawQuery("select * from student", null);
		bindList(cursor);
		
		Button btn=(Button)findViewById(R.id.btn_sno);
		btn.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				EditText et_sno=(EditText)findViewById(R.id.editText1);
				String sno=et_sno.getText().toString();
				Cursor cursor=db.rawQuery("select * from student where sno=?", new String[]{sno});
				bindList(cursor);
			}
			
			
		});
		
		Button btn2=(Button)findViewById(R.id.btn_refresh);
		btn2.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Cursor cursor=db.rawQuery("select * from student", null);
				bindList(cursor);
			}
			
		});
	}
	
	protected void bindList(Cursor cursor) {
		SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.line, cursor, new String[]{"sno","sname","major"},new int[]{R.id.textView1,R.id.textView2,R.id.textView3}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);			
		listView.setAdapter(adapter);		
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		if(dbHelper!=null){
			dbHelper.close();
		}
	}

}

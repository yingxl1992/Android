package com.example.homework4;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button confirm;
	Button list;
	StuDatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dbHelper=new StuDatabaseHelper(this,"homework4.db3",1);
		
		confirm=(Button)findViewById(R.id.btn_confirm);
		confirm.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				EditText no=(EditText)findViewById(R.id.et_no);
				EditText name=(EditText)findViewById(R.id.et_name);
				EditText major=(EditText)findViewById(R.id.et_major);
				String sno=no.getText().toString();
				String sname=name.getText().toString();
				String smajor=major.getText().toString();
				
				insertData(dbHelper.getWritableDatabase(),sno,sname,smajor);
				Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
			
			}
		
		});
		
		list=(Button)findViewById(R.id.btn_list);
		list.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,StuListActivity1.class);
				startActivity(intent);
			}
			
		});
	}
	
	private void insertData(SQLiteDatabase db, String sno,
			String sname, String smajor) {
		db.execSQL("insert into student values(null,?,?,?)", new String[]{sno,sname,smajor});
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		if(dbHelper!=null){
			dbHelper.close();
		}
	}
}

package com.example.homework4;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class StuListActivity1 extends Activity{
	
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
		ArrayList<Stu> list=new ArrayList<Stu>();
		Stu stu=null;
		while (cursor.moveToNext()) {  
            stu = new Stu();  
            stu.setSno(cursor.getString(cursor  
                    .getColumnIndex("sno")));  
            stu.setSname(cursor.getString(cursor  
                    .getColumnIndex("sname")));  
            stu.setMajor(cursor.getString(cursor  
                    .getColumnIndex("major")));  
            list.add(stu);  
        }
		MyAdapter adapter=new MyAdapter(this, list);			
		listView.setAdapter(adapter);		
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		if(dbHelper!=null){
			dbHelper.close();
		}
	}


	class MyAdapter extends BaseAdapter{
		private Context contxet;
	    private ArrayList<Stu> list;
	    private LayoutInflater mInflater;
	    
	    TextView tv_snoline;
        TextView tv_snameline;
        TextView tv_majorline;
        Button btn_edit;
        Button btn_delete;
	 
	    public MyAdapter(Context contxet, ArrayList<Stu> list) {
	        this.contxet = contxet;
	        this.list = list;
	    }

		public int getCount() {
			return list.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){ 
				mInflater = (LayoutInflater) contxet
		                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = mInflater.inflate(R.layout.line1, null);
	            tv_snoline = (TextView) convertView
	                    .findViewById(R.id.tv_sno);
	            tv_snameline = (TextView) convertView
	                    .findViewById(R.id.tv_sname);
	            tv_majorline = (TextView) convertView
	                    .findViewById(R.id.tv_major);
	            btn_edit = (Button) convertView
	                    .findViewById(R.id.btn_edit);
	            btn_delete = (Button) convertView
	                    .findViewById(R.id.btn_delete);
			}
	  
	        final Stu stu=list.get(position);
	        tv_snoline.setText(stu.getSno());
	        tv_snameline.setText(stu.getSname());
	        tv_majorline.setText(stu.getMajor());
	        btn_edit.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					
					
				}
	        	
	        });
	 
	        btn_delete.setOnClickListener(new OnClickListener() {

	            public void onClick(View v) {
	            	
	            	db.delete("student", "sno=?", new String[]{stu.getSno()});
	            	//Toast.makeText(,"添加成功",Toast.LENGTH_SHORT).show();
	            	Cursor cursor=db.rawQuery("select * from student", null);
					bindList(cursor);
	            	
	            }
	        });
	 
	        return convertView;
		}
	}
}

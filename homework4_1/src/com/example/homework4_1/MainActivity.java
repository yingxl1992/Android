package com.example.homework4_1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	ContentResolver contentResolver;
	Uri uri=Uri.parse("content://com.example.homework4.stuprovider");
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView=(ListView)findViewById(R.id.listView1);
		contentResolver=getContentResolver();
			
		Cursor cursor=contentResolver.query(uri, null, null, null, null);
		bindList(cursor);		
		
		Button btn=(Button)findViewById(R.id.btn_sno);
		btn.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				EditText et_sno=(EditText)findViewById(R.id.editText1);
				String sno=et_sno.getText().toString();
				Cursor cursor=contentResolver.query(uri, null, "sno=?", new String[]{sno}, null);
				bindList(cursor);
			}
			
			
		});
		
		Button btn2=(Button)findViewById(R.id.btn_refresh);
		btn2.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Cursor cursor=contentResolver.query(uri, null, null, null, null);
				bindList(cursor);
			}
			
		});
	}
	
	private void bindList(Cursor cursor){
		SimpleCursorAdapter adapter=new SimpleCursorAdapter(MainActivity.this, R.layout.line1, cursor, new String[]{"sno","sname","major"},new int[]{R.id.textView1,R.id.textView2,R.id.textView3}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);
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
	            	
	            	contentResolver.delete(uri, "sno=?",new String[]{stu.getSno()});
	            	//Toast.makeText(,"添加成功",Toast.LENGTH_SHORT).show();
	            	Cursor cursor=contentResolver.query(uri, null, null, null, null);
					bindList(cursor);
	            	
	            }
	        });
	 
	        return convertView;
		}
	}
}

package com.example.homework5;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	CalService.CalBinder binder;
	
	private ServiceConnection conn=new ServiceConnection(){

		public void onServiceConnected(ComponentName name, IBinder service) {
			unbindService(conn);
			binder=(CalService.CalBinder)service;
		}

		public void onServiceDisconnected(ComponentName name) {
			unbindService(conn);
			binder=null;
		}		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn_compare=(Button)findViewById(R.id.btn_compare);
		final Intent intent=new Intent();
		intent.setAction("com.example.homework5.CAL_SERVICE");
		bindService(intent,conn,Service.BIND_AUTO_CREATE);
		
		btn_compare.setOnClickListener(new OnClickListener(){
			
            public void onClick(View v) {
				EditText et_num1=(EditText)findViewById(R.id.et_num1);
				EditText et_num2=(EditText)findViewById(R.id.et_num2);
				String snum1=et_num1.getText().toString().trim();
				String snum2=et_num2.getText().toString().trim();
				int num1=Integer.parseInt(snum1);
				int num2=Integer.parseInt(snum2);
								
				int res=binder.compare(num1, num2);
				
				TextView tv_result=(TextView)findViewById(R.id.tv_result);
				String text="";
				if(res==1){
					text="num1 is bigger than num2";
				}else if(res==0){
					text="num1 is equal to num2";
				}else{
					text="num1 is less than num2";
				}
				tv_result.setText(text);
			}
			
		});
		
		Button btn_reset=(Button)findViewById(R.id.btn_reset);
		btn_reset.setOnClickListener(new OnClickListener(){
			
            public void onClick(View v) {
            	EditText et_num1=(EditText)findViewById(R.id.et_num1);
				EditText et_num2=(EditText)findViewById(R.id.et_num2);
				TextView tv_result=(TextView)findViewById(R.id.tv_result);
				et_num1.setText("");
				et_num2.setText("");
				tv_result.setText("");
            }
            
		});
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		unbindService(conn);
	}
}

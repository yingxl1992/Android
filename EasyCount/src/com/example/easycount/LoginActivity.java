package com.example.easycount;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
//	String usernameString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
//		int state=0;
////		可以实现已登陆后跳转
//		if (state==0) {
//			Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//			intent.putExtra("username", usernameString);
//			startActivity(intent);
//		}
//		
		
		
		Button btn_loginButton=(Button)findViewById(R.id.btn_login);
		Button btn_reg=(Button)findViewById(R.id.btn_reg);
		final EditText usernameEditText=(EditText)findViewById(R.id.et_username);
		final EditText passwordEditText=(EditText)findViewById(R.id.et_password);
		
		btn_loginButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {
					StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() 
					.detectDiskReads() 
					.detectDiskWrites() 
					.detectNetwork()
					.penaltyLog() 
					.build()); 
					StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder() 
					.detectLeakedSqlLiteObjects() 
					.detectLeakedClosableObjects() 
					.penaltyLog() 
					.penaltyDeath() 
					.build());
					
					String usernameString=usernameEditText.getText().toString();
					String passwordString=passwordEditText.getText().toString();
					
					SoapObject request =new SoapObject("http://ws.web.easycount.yxl/","getPwdByName");
					request.addProperty("username",usernameString);
					final SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.bodyOut = request;
					final HttpTransportSE ht=new HttpTransportSE("http://10.0.2.2:8080/easycountbg?wsdl");					
					ht.call(null, envelope);
					SoapObject soapObject =(SoapObject) envelope.bodyIn;					
					String pwd=soapObject.getPropertyAsString(0);
					if (pwd.equals(passwordString)==true) {
						Intent intent=new Intent(LoginActivity.this, MainActivity.class);
						intent.putExtra("username", usernameString);
						startActivity(intent);
					}else if (pwd.equals("没有结果")) {
						Toast.makeText(getApplicationContext(), "用户名不存在，请重试~", Toast.LENGTH_SHORT).show();
					}
					else {
						Toast.makeText(getApplicationContext(), "密码错误，请重试~", Toast.LENGTH_SHORT).show();
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btn_reg.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(LoginActivity.this,RegActivity.class);
				startActivity(intent);
			}
		});
	}

}

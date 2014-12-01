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

public class RegActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		
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
		
		Button btn_loginButton=(Button)findViewById(R.id.btn_login);
		btn_loginButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Intent intent=new Intent(RegActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
		});
		
		final EditText usernameEditText=(EditText)findViewById(R.id.et_username);
		final EditText passwordEditText=(EditText)findViewById(R.id.et_password);
		Button btn_regButton=(Button)findViewById(R.id.btn_reg);
		btn_regButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try{
					SoapObject request =new SoapObject("http://ws.web.easycount.yxl/","addUser");
					request.addProperty("username",usernameEditText.getText().toString());
					request.addProperty("password", passwordEditText.getText().toString());
					final SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.bodyOut = request;
					final HttpTransportSE ht=new HttpTransportSE("http://10.0.2.2:8080/easycountbg?wsdl");					
					ht.call(null, envelope);
					SoapObject soapObject =(SoapObject) envelope.bodyIn;
					int res=Integer.parseInt(soapObject.getPropertyAsString(0));
					if(res==1) {
						Toast.makeText(getApplicationContext(), "注册成功~", Toast.LENGTH_SHORT).show();
						Intent intent=new Intent(RegActivity.this,MainActivity.class);
						startActivity(intent);
					} else if(res==0) {
						Toast.makeText(getApplicationContext(), "注册失败，请重试~", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
	}
}

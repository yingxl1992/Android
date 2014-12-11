package com.example.homework6_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ConfigActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
				
		Button btn_login=(Button)findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				EditText tv_name=(EditText)findViewById(R.id.et_name);
				String name=tv_name.getText().toString();
				
				Intent intent=new Intent(ConfigActivity.this,MainActivity.class);
				intent.putExtra("name", name);
				startActivity(intent);
			}
			
		});
		
	}
	
}

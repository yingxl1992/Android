package com.example.easycount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		
		Button btn_loginButton=(Button)findViewById(R.id.btn_login);
		btn_loginButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(RegActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
	}
}

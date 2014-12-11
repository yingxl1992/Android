package com.example.helloworld;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class LoggedActivity extends LauncherActivity {
	
	String[] names={"Basic Info","Set Password"};
    Class<?>[] clazzs={InfoActivity.class,PasswordActivity.class};

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,names);
        setListAdapter(adapter);
	}
	
	@Override
	public Intent intentForPosition(int position){
		return new Intent(this,clazzs[position]);		
	}
}

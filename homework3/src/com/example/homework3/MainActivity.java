package com.example.homework3;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends LauncherActivity {
	
	String[] names={"Grace's Photoshow","Grace's going to lunch"};
    Class<?>[] clazzs={PhotoActivity.class,LunchActivity.class};

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

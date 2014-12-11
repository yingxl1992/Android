package com.example.helloworld;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class InfoActivity extends ListActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        String[] names={"username","password"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,names);
        setListAdapter(adapter);
	}

}

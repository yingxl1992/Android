package com.example.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	String loginType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        Button bt=(Button)findViewById(R.id.bt_login);
        bt.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){      
        		TextView show1=(TextView)findViewById(R.id.et_username);
        		TextView show2=(TextView)findViewById(R.id.et_password);       
        		
        		if(show1.getText().toString().equals(show2.getText().toString())){
        			System.out.println(show1.getText());
        			System.out.println(show2.getText());
        			showAlertDialog("Hello, "+loginType+" "+show1.getText().toString()+", Login Success!");	
        		}else{
        			System.out.println(show1.getText());
        			System.out.println(show2.getText());
        			showAlertDialog("Hello, "+loginType+" "+show1.getText().toString()+", Login Failed!");
        		}
        		
        	}
        });
        
        Button bt2=(Button)findViewById(R.id.bt_reset);
        bt2.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){        	
        		TextView show1=(TextView)findViewById(R.id.et_username);
        		show1.setText("");
        		TextView show2=(TextView)findViewById(R.id.et_password);
        		show2.setText("");
        	}
        });
        
        Button bt1=(Button)findViewById(R.id.button1);
        bt1.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){        	
        		Toast toast=Toast.makeText(MainActivity.this, "Don't have permission",Toast.LENGTH_SHORT);
    			toast.show();
        	}
        });
        
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.rg_loginType);
        final RadioButton radioButton1=(RadioButton)findViewById(R.id.rb_student);
        final RadioButton radioButton2=(RadioButton)findViewById(R.id.rb_teacher);
        
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==radioButton1.getId()){
					loginType=radioButton1.getText().toString();
				}else{
					loginType=radioButton2.getText().toString();
				}
			}
        	
        });
    }

    private void showAlertDialog(String msg) {
		AlertDialog.Builder builder =new AlertDialog.Builder(this);
		builder.setTitle("dialog");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage(msg);
		
		ComponentName comp=new ComponentName(this,LoggedActivity.class);
		final Intent intent=new Intent();
    	intent.setComponent(comp);
    	
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {      			
            @Override 
            public void onClick(DialogInterface dialog, int which) {              	
            	startActivity(intent);  
            } 
        });
		builder.setNegativeButton("取消", null);
		AlertDialog ad=builder.create();
		ad.show();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Register");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == 0) {
        	ComponentName comp=new ComponentName(this,RegActivity.class);
        	Intent intent=new Intent();
        	intent.setComponent(comp);
        	startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

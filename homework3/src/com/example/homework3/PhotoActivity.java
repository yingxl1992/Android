package com.example.homework3;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoActivity extends Activity{

	String[] names={"Grace's Photoshow","Grace's going to lunch"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		
	   Button start = (Button)findViewById(R.id.start);
       Button stop = (Button)findViewById(R.id.stop);
       final ImageView imageView=(ImageView)findViewById(R.id.imageView1);
       final AnimationDrawable anim=(AnimationDrawable)imageView.getBackground();
       
       start.setOnClickListener(new OnClickListener(){
	       	@Override
	       	public void onClick(View view){
	       		anim.start();
	       	}
       });
       
       stop.setOnClickListener(new OnClickListener(){
	       	@Override
	       	public void onClick(View view){
	       		anim.stop();
	       	}
       });
		
	}
}

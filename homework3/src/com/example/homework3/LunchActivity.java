package com.example.homework3;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LunchActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lunch);
		
		ImageView imageView=(ImageView)findViewById(R.id.imageView1);
		Animation anim=AnimationUtils.loadAnimation(this, R.anim.grace1);
		anim.setFillAfter(true);
		
		imageView.startAnimation(anim);
	}
}

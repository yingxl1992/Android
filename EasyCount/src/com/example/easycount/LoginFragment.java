package com.example.easycount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment {
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	public static LoginFragment newInstance(int sectionNumber) {
		
		LoginFragment fragment = new LoginFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	public LoginFragment() {
		
	}

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		View view=inflater.inflate(R.layout.activity_login, container, false);
		return view;
	}	
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {   
		super.onActivityCreated(savedInstanceState);   
        Button btn_login = (Button) getActivity().findViewById(R.id.btn_login);   
        btn_login.setOnClickListener(new OnClickListener() {   
	    	public void onClick(View v) {   
	    		//处理登录事件
	        }   
	    });
        Button btn_reg =(Button)getActivity().findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(), RegActivity.class);
				intent.putExtra("position", 1);
				startActivity(intent);				
			}
		});
    }  
}

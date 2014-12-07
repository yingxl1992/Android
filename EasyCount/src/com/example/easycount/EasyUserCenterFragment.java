package com.example.easycount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EasyUserCenterFragment extends Fragment {
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	Button btn_logoutButton;
	TextView tv_userTextView;
	
	public static EasyUserCenterFragment newInstance(int sectionNumber) {
		
		EasyUserCenterFragment fragment = new EasyUserCenterFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	public EasyUserCenterFragment() {
		
	}

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		View view=inflater.inflate(R.layout.fragment_usercenter, container, false);
		
		Intent intent=getActivity().getIntent();
		String usernameString=intent.getStringExtra("username");
		tv_userTextView=(TextView)view.findViewById(R.id.tv_user);
		tv_userTextView.setText(usernameString);
		
		btn_logoutButton=(Button)view.findViewById(R.id.btn_logout);
		btn_logoutButton.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
		return view;
	}	
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {   
		super.onActivityCreated(savedInstanceState);   
       
        
    }

}

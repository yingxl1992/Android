package com.example.easycount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExpendFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	
	public static ExpendFragment newInstance(int sectionNumber) {
		
		ExpendFragment fragment = new ExpendFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	public ExpendFragment() {
		
	}

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		View view=inflater.inflate(R.layout.fragment_expend, container, false);
		return view;
	}	
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {   
		super.onActivityCreated(savedInstanceState);   
        
    }  
}

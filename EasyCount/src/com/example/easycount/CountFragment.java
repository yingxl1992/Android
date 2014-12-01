package com.example.easycount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CountFragment extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	public static CountFragment newInstance(int sectionNumber) {
		
		CountFragment fragment = new CountFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	public CountFragment() {
		
	}

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		View view=inflater.inflate(R.layout.fragment_count, container, false);
		return view;
	}	
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {   
		super.onActivityCreated(savedInstanceState);   
        
    }

}

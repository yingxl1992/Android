package com.example.easycount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easycount.dao.CountDatabaseHelper;
import com.example.easycount.entity.RecordInfo;
import com.example.easycount.utils.CurrentDayUtil;

public class ExpendFragment extends ListFragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	SQLiteDatabase dbDatabase;
	ListView listView;
	TextView sumTextView;
	CountDatabaseHelper databaseHelper;
	float sum;
	private CurrentDayUtil currentDate;
	
	int[] imageIds=new int[]{R.drawable.breakfast,R.drawable.lunch,R.drawable.dinner,R.drawable.drinking,R.drawable.candy,
			R.drawable.bus,R.drawable.shopping,R.drawable.enter,R.drawable.user,R.drawable.cloth};
	String[] textStrings=new String[]{"早餐","午餐","晚餐","饮料","零食","交通","购物","娱乐","社交","衣物"};
	List<RecordInfo> records;
	
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		bindlist();		
	}

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		View view=inflater.inflate(R.layout.fragment_expend, container, false);	
		listView=(ListView)view.findViewById(android.R.id.list);
		sumTextView=(TextView)view.findViewById(R.id.tv_amount);
		String sumString=String.format("%.1f", sum);
        sumTextView.setText(sumString);
		return view;
	}	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {  
        super.onListItemClick(l, v, position, id);  
		Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();	
		Intent intent=new Intent(getActivity(), DetailActivity.class);
		Bundle bundle=new Bundle();
		bundle.putSerializable("record", records.get(position));
        intent.putExtra("info", bundle);
        intent.putExtra("currentDate", currentDate);
        startActivity(intent);
	}
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {   
		super.onActivityCreated(savedInstanceState);  		        
    } 

	@Override
	public void onResume(){
		super.onResume();
		setListAdapter(null);
        bindlist();	
        String sumString=String.format("%.1f", sum);
        sumTextView.setText(sumString);
	}
	
	public void bindlist() {
		int year;
		int month;
		int day;
		databaseHelper=new CountDatabaseHelper(getActivity(), "easycount.db3", 1);
		dbDatabase=databaseHelper.getReadableDatabase();	
		currentDate=(CurrentDayUtil)getArguments().get("currentDate");
		if (currentDate==null) {
			CurrentDayUtil currentDayUtil=new CurrentDayUtil();
			year=currentDayUtil.getYear();
			month=currentDayUtil.getMonth();
			day=currentDayUtil.getDay();
			currentDate=currentDayUtil;
		}else {
			year=currentDate.getYear();
			month=currentDate.getMonth();
			day=currentDate.getDay();
		}
		String sqlString="select * from expend where addTime='"+year+"/"+month+"/"+day+"'";
		System.out.println("sqlString="+sqlString);
		Cursor cursor=dbDatabase.rawQuery(sqlString,null);
		List<Map<String, Object>> listItems=new ArrayList<Map<String,Object>>();
		sum=0;
		records=new ArrayList<RecordInfo>();
		while (cursor.moveToNext()) {
			Map<String, Object> listItem=new HashMap<String, Object>();			
			listItem.put("typepic",imageIds[cursor.getInt(1)] );
			listItem.put("type", textStrings[cursor.getInt(1)]);
			listItem.put("money", cursor.getFloat(3));
			listItems.add(listItem);
			sum+=cursor.getFloat(3);
			
			RecordInfo info=new RecordInfo();
			info.setId(cursor.getString(0));
			info.setType(cursor.getInt(1));
			info.setMoney(cursor.getFloat(3));
			info.setDate(cursor.getString(2));
			info.setDes(cursor.getString(4));
			info.setAccountType(cursor.getInt(5));			
			records.add(info);
		}
		cursor.close();
		dbDatabase.close();
		databaseHelper.close();
		SimpleAdapter simpleAdapter=new SimpleAdapter(getActivity(), listItems, R.layout.line, new String[]{"typepic","type","money"},new int[]{R.id.iv_type,R.id.tv_type,R.id.tv_money});
		setListAdapter(simpleAdapter);			
	}

	public CurrentDayUtil getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(CurrentDayUtil currentDate) {
		this.currentDate = currentDate;
	}

	
}

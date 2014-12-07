package com.example.easycount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.easycount.dao.CountDatabaseHelper;
import com.example.easycount.entity.RecordInfo;
import com.example.easycount.utils.CurrentDayUtil;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DetailActivity extends Activity{
	
	ListView lv_intro;
	ListView lv_list;
	Button btn_del;
	int[] expendimageIds=new int[]{R.drawable.breakfast,R.drawable.lunch,R.drawable.dinner,R.drawable.drinking,R.drawable.candy,
			R.drawable.bus,R.drawable.shopping,R.drawable.enter,R.drawable.user,R.drawable.cloth};
	String[] expendtextStrings=new String[]{"早餐","午餐","晚餐","饮料","零食","交通","购物","娱乐","社交","衣物"};
	int[] listImageIds=new int[]{R.drawable.des,R.drawable.type,R.drawable.timer};
	String[] listStrings= new String[]{"描述","账户","日期"};
	String[] acountStrings=new String[]{"现金","银行卡"};
	
	int[] incomeimageIds=new int[]{R.drawable.salary,R.drawable.bonus,R.drawable.grants,R.drawable.invest,R.drawable.elseincome};
	String[] incometextStrings=new String[]{"薪水","奖金","补助费","投资","其它"};
	private CurrentDayUtil currentDayUtil;
	
	CountDatabaseHelper databaseHelper;
	SQLiteDatabase database;
	
	private RecordInfo recordInfos;
	private int flag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		Bundle infoBundle=intent.getBundleExtra("info");
		recordInfos=(RecordInfo) infoBundle.get("record");
		currentDayUtil=(CurrentDayUtil) intent.getSerializableExtra("currentDate");
		flag=intent.getIntExtra("flag", 0);
		if (flag==0) {
			CustomTitleBar.getTitleBar(this, "查看支出", 0,"编辑");
		}else {
			CustomTitleBar.getTitleBar(this, "查看收入", 0,"编辑");
		}
		
		
		setContentView(R.layout.activity_detail);
		
		int[] imageIds;
		String[] textStrings;
		if (flag==1) {
			imageIds=incomeimageIds;
			textStrings=incometextStrings;
		}else {
			imageIds=incomeimageIds;
			textStrings=expendtextStrings;
		}
		
		lv_intro=(ListView)findViewById(R.id.lv_intro);
		List<Map<String, Object>> introList=new ArrayList<Map<String,Object>>();
		Map<String, Object> introMap=new HashMap<String,Object>();		
		introMap.put("typepic", imageIds[recordInfos.getType()]);
		introMap.put("type", textStrings[recordInfos.getType()]);
		introMap.put("money", recordInfos.getMoney());
		introList.add(introMap);
		
		SimpleAdapter simpleAdapter=new SimpleAdapter(getApplicationContext(), introList, R.layout.line, new String[]{"typepic","type","money"},new int[]{R.id.iv_type,R.id.tv_type,R.id.tv_money});
		lv_intro.setAdapter(simpleAdapter);	
		
		lv_list=(ListView)findViewById(R.id.lv_list);
		List<Map<String, Object>> listList=new ArrayList<Map<String,Object>>();
		Map<String, Object> listItem1=new HashMap<String,Object>();
		listItem1.put("pic", listImageIds[0]);
		listItem1.put("title", listStrings[0]);
		listItem1.put("subtitle", recordInfos.getDes());		
		listList.add(listItem1);
		Map<String, Object> listItem2=new HashMap<String,Object>();
		listItem2.put("pic", listImageIds[1]);
		listItem2.put("title", listStrings[1]);
		listItem2.put("subtitle", acountStrings[recordInfos.getAccountType()]);		
		listList.add(listItem2);
		Map<String, Object> listItem3=new HashMap<String,Object>();
		listItem3.put("pic", listImageIds[2]);
		listItem3.put("title", listStrings[2]);
		listItem3.put("subtitle", recordInfos.getDate());		
		listList.add(listItem3);
		SimpleAdapter simpleAdapter2=new SimpleAdapter(getApplicationContext(), listList, R.layout.addlistline, new String[]{"pic","title","subtitle"}, new int[]{R.id.iv_image,R.id.tv_des,R.id.tv_subtitle});
		lv_list.setAdapter(simpleAdapter2);
		
		databaseHelper=new CountDatabaseHelper(getApplicationContext(), "easycount.db3", 1);
		database=databaseHelper.getReadableDatabase();	
		btn_del=(Button)findViewById(R.id.btn_del);
		btn_del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String sqlString="delete from expend where _id="+recordInfos.getId();
				database.execSQL(sqlString);
				finish();
			}
		});
	}

	public RecordInfo getRecordInfos() {
		return recordInfos;
	}

	public void setRecordInfos(RecordInfo recordInfos) {
		this.recordInfos = recordInfos;
	}
	
	@Override
	public void onRestart(){
		super.onRestart();
		finish();
	}

	public CurrentDayUtil getCurrentDayUtil() {
		return currentDayUtil;
	}

	public void setCurrentDayUtil(CurrentDayUtil currentDayUtil) {
		this.currentDayUtil = currentDayUtil;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}

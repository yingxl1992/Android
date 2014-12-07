package com.example.easycount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.easycount.entity.RecordInfo;
import com.example.easycount.utils.CurrentDayUtil;

public class AddActivity extends Activity {

	GridView gridView;
	ListView listView;
	int[] imageIds=new int[]{R.drawable.breakfast,R.drawable.lunch,R.drawable.dinner,R.drawable.drinking,R.drawable.candy,
			R.drawable.bus,R.drawable.shopping,R.drawable.enter,R.drawable.user,R.drawable.cloth};
	String[] textStrings=new String[]{"早餐","午餐","晚餐","饮料","零食","交通","购物","娱乐","社交","衣物"};
	
	int[] incomeimageIds=new int[]{R.drawable.salary,R.drawable.bonus,R.drawable.grants,R.drawable.invest,R.drawable.elseincome};
	String[] incometextStrings=new String[]{"薪水","奖金","补助费","投资","其它"};
	
	int[] listImageIds=new int[]{R.drawable.des,R.drawable.type};
	String[] listStrings= new String[]{"描述","账户"};
	String[] acountStrings=new String[]{"现金","银行卡"};
	RecordInfo recordInfo=null;
	View preView;
	private int accountType=0;
	private String des="";
	private int type=-1;
	private String id="";
	List<Map<String, Object>> showListItems;
	List<Map<String, Object>> listItems;
	SimpleAdapter simpleAdapter;
	SimpleAdapter simpleAdapter2;
	int flag;
	int pos;
	private CurrentDayUtil currentDayUtil;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent=getIntent();
		flag=intent.getIntExtra("flag", 0);
		pos=intent.getIntExtra("currentpos", 0);
		if(flag==0&&pos==0){
			CustomTitleBar.getTitleBar(this, "添加支出",0,"保存");
		}else if(flag==1&&pos==0){
			CustomTitleBar.getTitleBar(this, "编辑支出", 1,"保存");
			recordInfo=(RecordInfo) intent.getSerializableExtra("item");
		}else if(flag==0&&pos==1){
			CustomTitleBar.getTitleBar(this, "添加收入", 2, "保存");
		}else {
			CustomTitleBar.getTitleBar(this, "编辑收入", 3,"保存");
			recordInfo=(RecordInfo) intent.getSerializableExtra("item");
		}
		currentDayUtil=(CurrentDayUtil) intent.getSerializableExtra("currentDate");
		
		setContentView(R.layout.activity_add);
		
		if (recordInfo!=null) {
			id=recordInfo.getId();
			accountType=recordInfo.getAccountType();
			des=recordInfo.getDes();
			type=recordInfo.getType();
			EditText et_money=(EditText)findViewById(R.id.et_money);
			et_money.setText(""+recordInfo.getMoney());
		}
				
		listItems=new ArrayList<Map<String,Object>>();
		if (pos==0) {
			for (int i = 0; i < imageIds.length; i++) {
				Map<String, Object> listItem=new HashMap<String, Object>();
				listItem.put("image", imageIds[i]);
				listItem.put("title", textStrings[i]);
				listItems.add(listItem);
			}
		}else {
			for (int i = 0; i < incomeimageIds.length; i++) {
				Map<String, Object> listItem=new HashMap<String, Object>();
				listItem.put("image", incomeimageIds[i]);
				listItem.put("title", incometextStrings[i]);
				listItems.add(listItem);
			}
		}
		
		
		simpleAdapter=new SimpleAdapter(getApplicationContext(), listItems, R.layout.cell, new String[]{"image","title"},new int[]{R.id.imageView1,R.id.textView1});
		gridView=(GridView)findViewById(R.id.gridView1);
		gridView.setAdapter(simpleAdapter);
		gridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
				
		gridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (preView!=null) {
					preView.setBackgroundDrawable(null);
				}
				preView=view;
				view.setBackgroundColor(R.color.blue);
				Toast.makeText(getApplicationContext(), "选中了"+textStrings[position], Toast.LENGTH_SHORT)
				.show();
				type=position;
				
			}
		});
		
		showListItems=new ArrayList<Map<String, Object>>();
		
		Map<String, Object> listItem1=new HashMap<String, Object>();
		listItem1.put("pic", listImageIds[0]);
		listItem1.put("title", listStrings[0]);
		listItem1.put("subtitle", des);
		showListItems.add(listItem1);
		
		Map<String, Object> listItem2=new HashMap<String, Object>();
		listItem2.put("pic", listImageIds[1]);
		listItem2.put("title", listStrings[1]);
		listItem2.put("subtitle",acountStrings[accountType]);
		showListItems.add(listItem2);
		
		simpleAdapter2=new SimpleAdapter(getApplicationContext(), showListItems, R.layout.addlistline, new String[]{"pic","title","subtitle"}, new int[]{R.id.iv_image,R.id.tv_des,R.id.tv_subtitle});
		listView=(ListView)findViewById(R.id.listView1);
		listView.setAdapter(simpleAdapter2);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position==1) {
					showPopDialog();
				}else if (position==0) {
					showTextDialog();
				}
				
			}
		});
		
		
	}

	protected void showPopDialog() {
		final int preType=accountType;
		AlertDialog.Builder builder=new AlertDialog.Builder(this).setTitle("选择账户").setSingleChoiceItems(R.array.accountType, accountType, new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				accountType=which;				
			}
		});
		builder.setPositiveButton("确定", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				recordInfo.setAccountType(accountType);
				HashMap<String, Object> item=(HashMap<String, Object>)simpleAdapter2.getItem(1);
				item.put("subtitle", acountStrings[accountType]);
				simpleAdapter2.notifyDataSetChanged();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				accountType=preType;
			}
		});
		builder.create().show();
		
	}
	
	protected void showTextDialog() {
		final LinearLayout textForm=(LinearLayout)getLayoutInflater().inflate(R.layout.activity_adddes, null);
		final String preDes=des;
		new AlertDialog.Builder(this).setTitle("编辑描述").setView(textForm).setPositiveButton("保存", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				EditText editText=(EditText)textForm.findViewById(R.id.editText1);
				des=editText.getText().toString();
				HashMap<String, Object> item=(HashMap<String, Object>)simpleAdapter2.getItem(0);
				item.put("subtitle", des);
				simpleAdapter2.notifyDataSetChanged();
			}
		}).setNegativeButton("取消", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				des=preDes;				
			}
		}).create().show();
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CurrentDayUtil getCurrentDayUtil() {
		return currentDayUtil;
	}

	public void setCurrentDayUtil(CurrentDayUtil currentDayUtil) {
		this.currentDayUtil = currentDayUtil;
	}	
}

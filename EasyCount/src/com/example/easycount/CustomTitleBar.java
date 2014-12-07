package com.example.easycount;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easycount.dao.CountDatabaseHelper;
import com.example.easycount.entity.RecordInfo;
import com.example.easycount.utils.CurrentDayUtil;

public class CustomTitleBar {
	private static Activity mActivity;

	public static void getTitleBar(Activity activity, String title, final int flag,String rtext) {
		mActivity = activity;
		
		activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		activity.setContentView(R.layout.navigationlist);
		activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.navigationlist);
		
		TextView titleTextView = (TextView) activity.findViewById(R.id.title);
		titleTextView.setText(title);
				
		Button backImageView = (Button) activity
				.findViewById(R.id.back);
		backImageView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(mActivity.getApplicationContext(), "点击了返回",
						Toast.LENGTH_SHORT).show();
				mActivity.finish();
			}
		});

		Button saveImageView = (Button) activity
				.findViewById(R.id.save);
		saveImageView.setText(rtext);
		saveImageView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mActivity instanceof AddActivity) {
					AddActivity addActivity=(AddActivity)mActivity;
					EditText moneyEditText=(EditText)mActivity.findViewById(R.id.et_money);
					String moneyString=moneyEditText.getText().toString();
					if (moneyString.equals("")||Float.parseFloat(moneyString)==0) {
						Toast.makeText(mActivity.getApplicationContext(), "请输入金额",
								Toast.LENGTH_SHORT).show();
					}else {
						float money=Float.parseFloat(moneyString);
						String des = addActivity.getDes();
						int accountType=addActivity.getAccountType();
						
						int type=addActivity.getType();
						if(type==-1){
							Toast.makeText(mActivity.getApplicationContext(), "请输入类别",
									Toast.LENGTH_SHORT).show();
						}else {

							CurrentDayUtil currentDayUtil=((AddActivity) mActivity).getCurrentDayUtil();
							String addTime = currentDayUtil.getYear()+ "/" + currentDayUtil.getMonth()+ "/"
									+currentDayUtil.getDay();	
													
							System.out.println("ADDtIME"+addTime);						
							CountDatabaseHelper dbHelper = new CountDatabaseHelper(
									mActivity.getApplicationContext(), "easycount.db3", 1);
							SQLiteDatabase database = dbHelper.getReadableDatabase();		
							String sqlString;
							if (flag==0) {
								sqlString = "insert into expend(type,addTime,money,des,accountType,addyear,addmonth,addday) values(" + type + ",'" + addTime + "'," + money + ",'" + des + "'," + accountType + ","+currentDayUtil.getYear()+","+currentDayUtil.getMonth()+","+currentDayUtil.getDay()+")";
							}else if(flag==1){
								sqlString="update expend set type="+type+",money="+money+",des='"+des+"',accountType="+accountType+" where _id="+addActivity.getId();
							}else if (flag==2) {
								sqlString = "insert into income(type,addTime,money,des,accountType,addyear,addmonth,addday) values(" + type + ",'" + addTime + "'," + money + ",'" + des + "'," + accountType + ","+currentDayUtil.getYear()+","+currentDayUtil.getMonth()+","+currentDayUtil.getDay()+")";
							}else {
								sqlString="update income set type="+type+",money="+money+",des='"+des+"',accountType="+accountType+" where _id="+addActivity.getId();
							}
							
							database.execSQL(sqlString);
							Toast.makeText(mActivity, "保存成功", Toast.LENGTH_SHORT).show();
							database.close();
							
							mActivity.finish();
						}						
					}
				}else if(mActivity instanceof DetailActivity){
					DetailActivity detailActivity=(DetailActivity)mActivity;
					RecordInfo recordInfo=detailActivity.getRecordInfos();
					Intent intent=new Intent(mActivity, AddActivity.class);
					intent.putExtra("currentpos",((DetailActivity) mActivity).getFlag());
					intent.putExtra("flag", 1);
					intent.putExtra("item",recordInfo);
					intent.putExtra("currentDate", ((DetailActivity) mActivity).getCurrentDayUtil());
					mActivity.startActivity(intent);
				}		
			}
		});
	}

}

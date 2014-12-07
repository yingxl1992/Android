package com.example.easycount;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.easycount.dao.CountDatabaseHelper;
import com.example.easycount.utils.CurrentDayUtil;

public class IncomeCountFragment extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	CurrentDayUtil currentDayUtil;
	CountDatabaseHelper databaseHelper;
	SQLiteDatabase database;
	LinearLayout linearLayout;
	Button btn_year;
	Button btn_month;
	Button btn_day;
	int flag=0;
	View view;
	
	String[] textStrings=new String[]{"薪水","奖金","补助费","投资","其它"};
	
	public static IncomeCountFragment newInstance(int sectionNumber) {
		
		IncomeCountFragment fragment = new IncomeCountFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	public IncomeCountFragment() {
		
	}

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		view =inflater.inflate(R.layout.fragment_count, container, false);
		linearLayout=(LinearLayout)view.findViewById(R.id.chart);
		
		btn_day=(Button)view.findViewById(R.id.btn_day);
		btn_day.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				flag=0;		
				genChart();
			}
		});
		
		btn_month=(Button)view.findViewById(R.id.btn_month);
		btn_month.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				flag=1;
				genChart();
			}
		});
		
		btn_year=(Button)view.findViewById(R.id.btn_year);
		btn_year.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				flag=2;		
				genChart();
			}
		});
		
		genChart();
		return view;
	}	
	
	protected void genChart() {
		if(currentDayUtil==null){
			currentDayUtil=new CurrentDayUtil();
		}
		databaseHelper=new CountDatabaseHelper(getActivity(), "easycount.db3",1);
		database=databaseHelper.getReadableDatabase();
		
		linearLayout.removeAllViews();
		
		List<Float> valueList=getValues();
		database.close();
		databaseHelper.close();

        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        View chartview = ChartFactory.getPieChartView(view.getContext(), buildCategoryDataset("Project budget", valueList), renderer);
        chartview.setBackgroundColor(Color.BLACK);
        linearLayout.addView(chartview);
	}

	private List<Float> getValues() {
		String addTime=currentDayUtil.getYear()+"/"+currentDayUtil.getMonth()+"/"+currentDayUtil.getDay();
		List<Float> list=new ArrayList<Float>();
		for (int i = 0; i < 5; i++) {
			String sqlString;
			if (flag==0) {
				sqlString="select sum(money) from income where addTime='"+addTime+"' and type="+i;
			}else if (flag==1) {
				sqlString="select sum(money) from income where addYear="+currentDayUtil.getYear()+" and addmonth="+currentDayUtil.getMonth()+" and type="+i;
			}else {
				sqlString="select sum(money) from income where addYear="+currentDayUtil.getYear()+" and type="+i;
			}
			
			Cursor cursor=database.rawQuery(sqlString, null);
			float value=0;
			if (cursor.moveToNext()) {
				value=cursor.getFloat(0);
			}
			list.add(value);
			cursor.close();
		}
		
		return list;
	}

	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {   
		super.onActivityCreated(savedInstanceState);   
		
    }
	
	protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[] { 20, 30, 15, 0 });
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title, List<Float> valueList) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (float value : valueList) {
            series.add(textStrings[k++], value);
        }
        return series;
    }

}

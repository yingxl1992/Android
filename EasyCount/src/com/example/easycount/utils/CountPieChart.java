package com.example.easycount.utils;

import org.achartengine.chart.PieChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;

public class CountPieChart{

	CategorySeries categorySeries=new CategorySeries("title");
	PieChart pieChart=new PieChart(categorySeries,new DefaultRenderer());
}

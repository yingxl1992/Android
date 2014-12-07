package com.example.easycount.utils;

import java.io.Serializable;
import java.util.Calendar;

public class CurrentDayUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int year;
	private int month;
	private int day;
	
	public CurrentDayUtil() {
		this.setYear(Calendar.getInstance().get(Calendar.YEAR));
		this.setMonth(Calendar.getInstance().get(Calendar.MONTH)+1);
		this.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	}
	public CurrentDayUtil(int y,int m,int d) {
		this.setYear(y);
		this.setMonth(m);
		this.setDay(d);
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
}

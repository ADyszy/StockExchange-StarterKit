package com.capgemini.marketplace.model;

import com.google.common.collect.ComparisonChain;

public class Date implements Comparable<Date> {
	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}
	
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public int compareTo(Date that) {
	    return ComparisonChain.start()
	         .compare(this.year, that.getYear())
	         .compare(this.month, that.getMonth())
	         .compare(this.day, that.getDay())
	         .result();
	}

}

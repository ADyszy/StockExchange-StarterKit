package com.capgemini.marketplace.data;

public class DataObject {
	
	private String name;
	private String date;
	private String rate;
	
	public DataObject(String name, String date, String rate) {
		setDate(date);
		setName(name);
		setRate(rate);
	}
	
	public String getName() {
		return name;
	}
	public String getDate() {
		return date;
	}
	public String getRate() {
		return rate;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
			
}

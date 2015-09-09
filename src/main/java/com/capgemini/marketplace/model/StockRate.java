package com.capgemini.marketplace.model;

public class StockRate implements Comparable<StockRate> {
	private Date date;
	private double value;
	
	public StockRate(Date dateResult, double value) {
		setDate(dateResult);
		setValue(value);
	}
	
	public Date getDate() {
		return date;
	}
	public double getValue() {
		return value;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setValue(double value) {
		this.value = value;
	}

	public int compareTo(StockRate o) {
		return this.date.compareTo(((StockRate) o).getDate());
	}
	
}

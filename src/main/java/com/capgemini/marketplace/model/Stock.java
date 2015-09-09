package com.capgemini.marketplace.model;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	private String name;
	private List<StockRate> history;
	public String getName() {
		return name;
	}
	
	public Stock() {
		history = new ArrayList<StockRate>();
	}
	
	public List<StockRate> getHistory() {
		return history;
	}
	
	public StockRate getActualRate() {
		return this.history.get(this.history.size()-1);
	}
	
	public void setHistory(List<StockRate> history) {
		this.history = history;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String str = name + ":\n";
		for (StockRate stockRate : history)
			str += stockRate.getDate() + " : " + stockRate.getValue() + "\n";
		return str;
	}
	
}

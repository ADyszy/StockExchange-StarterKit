package com.capgemini.marketplace.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.marketplace.data.DataService;
 
public class StockExchange {
	
	private int day = 1;
	private boolean end = false;
	
	@Autowired
	private DataService dataService;
	
	private List<Stock> actualStocks;
	
	public List<Stock> getStocks() {
		return actualStocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.actualStocks = stocks;
	}

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	public boolean updateStocks() {
		List<Stock> list = dataService.getActuallStocks(day);
		if (list == null) {
			return !(end = true);
			// hmm..
		}
		actualStocks = list;
		return goToNewDay();
	}
	
	private boolean goToNewDay(){
		day++;
		return !end;
	}
	
	public boolean simulationEnded(){
		return end;
	}
}

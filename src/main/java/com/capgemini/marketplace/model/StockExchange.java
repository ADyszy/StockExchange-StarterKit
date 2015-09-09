package com.capgemini.marketplace.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.marketplace.data.DataService;
 
public class StockExchange {
	
	private int day = 0;
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
	
	public void updateStocks() {
		List<Stock> list = dataService.getActuallStocks(day);
		if (list == null) {
			end = true;
			return;
		}
		actualStocks = list;
	}
	
	public boolean goToNewDay(){
		day++;
		return !end;
	}
	
	public boolean simulationEnded(){
		return end;
	}
}

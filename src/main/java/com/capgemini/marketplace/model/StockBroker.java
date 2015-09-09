package com.capgemini.marketplace.model;

import org.springframework.beans.factory.annotation.Autowired;

public class StockBroker {
	
	@Autowired
	private StockExchange stockExchange;
	
	public StockExchange getMarketPlace() {
		return stockExchange;
	}

	public void setMarketPlace(StockExchange marketPlace) {
		this.stockExchange = marketPlace;
	}
		
}

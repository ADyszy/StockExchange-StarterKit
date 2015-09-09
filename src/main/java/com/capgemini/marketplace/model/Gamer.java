package com.capgemini.marketplace.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gamer {
	
	@Autowired
	private StockBroker stockBrocker;
	
	@Autowired
	CashWallet wallet;
	
	public StockBroker getStockBrocker() {
		return stockBrocker;
	}
	
	public void setStockBrocker(StockBroker stockBrocker) {
		this.stockBrocker = stockBrocker;
	}
}

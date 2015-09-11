package com.capgemini.marketplace.strategy.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.marketplace.model.Gamer;
import com.capgemini.marketplace.model.StockExchange;
import com.capgemini.marketplace.strategy.BuyAndSellStrategy;

public class StupidStrategy extends BuyAndSellStrategy{
	
	private static int i = 0;
	
	@Autowired
	private StockExchange stockExchange;
	
	@Override
	public void play(Gamer gamer) {
		System.out.println(i++);
	}

	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}

}

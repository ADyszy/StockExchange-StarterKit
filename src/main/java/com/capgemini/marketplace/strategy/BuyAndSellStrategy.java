package com.capgemini.marketplace.strategy;

import com.capgemini.marketplace.model.Gamer;
import com.capgemini.marketplace.model.StockExchange;

public abstract class BuyAndSellStrategy {
	
	protected StockExchange stockExchange;
	protected Gamer gamer;
	
	public BuyAndSellStrategy(Gamer gamer) {
		this.gamer = gamer;
		stockExchange = gamer.getStockExchange();
	}
	
	public abstract void play();
	
}

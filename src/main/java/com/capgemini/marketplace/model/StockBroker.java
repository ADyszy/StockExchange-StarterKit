package com.capgemini.marketplace.model;

import com.capgemini.marketplace.calculation.StockCalculations;

public class StockBroker {
	
	private static final double PERCENT = 0.005; 
	
	public double buyCost(Stock stock, int amount) {
		double cost = stock.getActualRate().getValue();
		return StockCalculations.round((cost + margin(cost))*amount, 2);
	}
	
	public double sellCost(Stock stock, int amount){
		double cost = stock.getActualRate().getValue();
		return StockCalculations.round((cost - margin(cost))*amount, 2);
	}
	
	public double margin(double value) {
		return PERCENT*value;
	}
	
}

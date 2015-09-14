package com.capgemini.marketplace.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StockBroker {
	
	private static final double PERCENT = 0.005; 
	
	public double buyCost(Stock stock, int amount) {
		double cost = stock.getActualRate().getValue();
		return round((cost + margin(cost))*amount, 2);
	}
	
	public double sellCost(Stock stock, int amount){
		double cost = stock.getActualRate().getValue();
		return round((cost - margin(cost))*amount, 2);
	}
	
	public double margin(double value) {
		return PERCENT*value;
	}
	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
		
}

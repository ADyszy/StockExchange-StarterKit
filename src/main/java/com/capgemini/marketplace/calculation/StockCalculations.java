package com.capgemini.marketplace.calculation;

public class StockCalculations {
	
	public static int maxStockAmountForCash(double stockCost, double cash) {
		int result;
		for(result=0; result<=cash; result++)
			if(result*stockCost > cash) break;
		return result-1;
	}
	
}

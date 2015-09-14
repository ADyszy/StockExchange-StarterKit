package com.capgemini.marketplace.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StockCalculations {
	
	public static int maxStockAmountForCash(double stockCost, double cash) {
		int result;
		for(result=0; result<=cash; result++)
			if(result*stockCost > cash) break;
		return result-1;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
}

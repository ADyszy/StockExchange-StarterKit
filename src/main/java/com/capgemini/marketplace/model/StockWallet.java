package com.capgemini.marketplace.model;

import java.util.HashMap;
import java.util.Map;

public class StockWallet {
	private Map<Stock, Integer> shares;
	
	public StockWallet() {
		setShares(new HashMap<>());
	}
	
	public double getWalletValue(){
		return 0;
	}

	public Map<Stock, Integer> getShares() {
		return shares;
	}

	public void setShares(Map<Stock, Integer> shares) {
		this.shares = shares;
	}
}

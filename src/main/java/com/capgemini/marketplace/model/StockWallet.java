package com.capgemini.marketplace.model;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.marketplace.exception.NotOwnedStockReturnException;

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
	
	public void getBoughtStocks(Stock stock, int amount) {
		if (shares.containsKey(stock)) 
			amount += shares.get(stock);
		shares.put(stock, amount);
	}
	
	public boolean returnSoldStocks(Stock stock, int amount) throws NotOwnedStockReturnException {
		if (!shares.containsKey(stock)) throw new NotOwnedStockReturnException();
		if (shares.get(stock) < amount) throw new NotOwnedStockReturnException();
		shares.put(stock, shares.get(stock)-amount);
		return true;
	}
	
	public int getAmountOfOwnedStock(String stockName){
		Stock stock = getStockByName(stockName);
		if(stock == null) return 0;
		return this.shares.get(stock);
	}
	
	private Stock getStockByName(String name) {
		for (Stock stock : this.shares.keySet()) {
			if (stock.getName().equals(name))
				return stock;
		}
		return null;
	}
	
	public double totalValue() {
		double total = 0;
		for (Stock stock : this.shares.keySet()) {
			total += stock.finalValue(shares.get(stock));
		}
		return total;
	}
	
}

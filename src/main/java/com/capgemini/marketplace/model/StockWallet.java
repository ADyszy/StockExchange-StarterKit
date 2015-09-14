package com.capgemini.marketplace.model;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.marketplace.exception.NotOwnedStockReturnException;

public class StockWallet {
	private Map<Stock, Integer> shares;
	
	public StockWallet() {
		setShares(new HashMap<>());
	}
	
	public Map<Stock, Integer> getShares() {
		return shares;
	}

	public void setShares(Map<Stock, Integer> shares) {
		this.shares = shares;
	}
	
	// TODO it better.. 
	public void getBoughtStocks(Stock stock, int amount) {
		Stock tmp = getStockByName(stock.getName());
		if (tmp != null) {
			amount += shares.get(tmp);
			shares.put(tmp, amount);
			return;
		}
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
	
	public double totalValue(StockBroker stockBroker) {
		double total = 0;
		for (Stock stock : this.shares.keySet()) {
			double stockValue = stock.finalValue(shares.get(stock));
			total += stockValue - stockBroker.margin(stockValue);
		}
		return total;
	}
	
	@Override
	public String toString() {
		String stocks = "";
		for (Stock key : getShares().keySet()) {
			stocks += key.getName() + " (" + getShares().get(key) + ") " + "act. rate: " + key.getActualRate().getValue() + "\n";
		}
		return stocks;
	}
	
}

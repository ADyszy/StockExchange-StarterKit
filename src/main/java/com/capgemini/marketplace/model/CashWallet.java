package com.capgemini.marketplace.model;

import com.capgemini.marketplace.calculation.StockCalculations;
import com.capgemini.marketplace.exception.TooPoorWalletException;

public class CashWallet {
	private double cash;

	public CashWallet(double startCash) {
		setCash(startCash);
	}
	
	public double getCash() {
		return StockCalculations.round(cash, 2);
	}

	public void setCash(double cash) {
		this.cash = cash;
	}
	
	public void pay(double amount) throws TooPoorWalletException {
		if (!isAffordable(amount)) throw new TooPoorWalletException();
		cash -= amount;
	}
	
	public void earn(double amount){
		cash += amount;
	}
	
	public boolean isAffordable(double amount) {
		return (this.cash >= amount);
	}
}

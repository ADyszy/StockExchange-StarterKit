package com.capgemini.marketplace.model;


public class CashWallet {
	private double cash;

	public CashWallet(double startCash) {
		setCash(startCash);
	}
	
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}
	
}

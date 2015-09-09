package com.capgemini.marketplace.model;

import java.util.Map;

public class ShareWallet {
	private Map<String, Integer> shares;
	
	public double getWalletValue(){
		return 0;
	}

	public Map<String, Integer> getShares() {
		return shares;
	}

	public void setShares(Map<String, Integer> shares) {
		this.shares = shares;
	}
}

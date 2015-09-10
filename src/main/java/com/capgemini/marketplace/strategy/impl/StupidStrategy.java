package com.capgemini.marketplace.strategy.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.capgemini.marketplace.model.CashWallet;
import com.capgemini.marketplace.model.Gamer;
import com.capgemini.marketplace.model.Stock;
import com.capgemini.marketplace.model.StockWallet;
import com.capgemini.marketplace.strategy.BuyAndSellStrategy;

public class StupidStrategy extends BuyAndSellStrategy{

	@Override
	public void play(Gamer gamer) {
		if (gamer == null) return;
		sellRandom(gamer);
		buyRandom(gamer);
	}
	
	private Stock chooseRandomStockToBuy(List<Stock> stocks) {
		Collections.shuffle(stocks);
		return stocks.get(0);
	}
	
	private void buyRandom(Gamer gamer) {
		List<Stock> stocks = gamer.getStockExchange().getStocks();	
		if (stocks == null) return;
		double maxCost = gamer.getCashWallet().getCash();
		
		CashWallet cwallet = gamer.getCashWallet();
		StockWallet swallet = gamer.getStockWallet();
		Stock chosen = chooseRandomStockToBuy(stocks);
		
		int amount;
		for (amount=0; gamer.getStockBrocker().buyCost(chosen, amount) < 0.5 * maxCost; amount++){}
		cwallet.setCash(cwallet.getCash() - gamer.getStockBrocker().buyCost(chosen, amount));
		swallet.getShares().put(chosen, amount);
	}
	
	private void sellRandom(Gamer gamer) {
		if(gamer.getStockWallet().getShares().size() == 0) return;
				
		CashWallet cwallet = gamer.getCashWallet();
		StockWallet swallet = gamer.getStockWallet();
		Stock chosen = chooseRandomStockToBuy(new ArrayList<>(swallet.getShares().keySet()));
		int amount = (int) Math.floor(swallet.getShares().get(chosen)/5);
		cwallet.setCash(cwallet.getCash() + gamer.getStockBrocker().sellCost(chosen, amount));
		swallet.getShares().put(chosen, swallet.getShares().get(chosen)-amount);
	}

}

package com.capgemini.marketplace.strategy.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.marketplace.calculation.StockCalculations;
import com.capgemini.marketplace.exception.NotOwnedStockReturnException;
import com.capgemini.marketplace.exception.TooPoorWalletException;
import com.capgemini.marketplace.model.Gamer;
import com.capgemini.marketplace.model.Stock;
import com.capgemini.marketplace.model.StockExchange;
import com.capgemini.marketplace.strategy.BuyAndSellStrategy;

public class StupidRandomStrategy extends BuyAndSellStrategy{
	
	public StupidRandomStrategy(Gamer gamer) {
		super(gamer);
	}

	@Override
	public void play() {
		try {
			buyOneRandomStockForHalfFortune();
			sellHalfAmountFromRandomStock();
		} catch (TooPoorWalletException e) {
			e.printStackTrace();
		} catch (NotOwnedStockReturnException e) {
			e.printStackTrace();
		}
	}

	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}
	
	private void buyOneRandomStockForHalfFortune() throws TooPoorWalletException{
		if(this.stockExchange.getStocks()==null || 
				this.stockExchange.getStocks().isEmpty() || 
				this.stockExchange.getStocks()==null)
			return;
		Stock chosenStock = getRandomStockFromList(this.stockExchange.getStocks());
		double cash = (double)gamer.getCashWallet().getCash()/2;
		int amount = StockCalculations.maxStockAmountForCash(chosenStock.finalValue(1), cash);
		gamer.buyStocks(chosenStock, amount);
	}
	
	private void sellHalfAmountFromRandomStock() throws NotOwnedStockReturnException {
		if(gamer.getStockWallet().getShares().isEmpty() || 
				gamer.getStockWallet().getShares() == null)
			return;
		Stock chosenStock = getRandomStockFromGamersStockWallet();
		// TODO: make a method in StockWallet accessing amount of chosen stock.
		// to avoid sth like lower.
		int availableAmount = gamer.getStockWallet().getShares().get(chosenStock);
		gamer.sellStocks(chosenStock, Math.floorMod(availableAmount, 2));
	}
	
	// TODO: empty list exception !
	private Stock getRandomStockFromList(List<Stock> stockList) {
		Collections.shuffle(stockList);
		return stockList.get(0);
	}
	
	private Stock getRandomStockFromGamersStockWallet(){
		Set<Stock> set = gamer.getStockWallet().getShares().keySet();
		List<Stock> list = new ArrayList<>(set);
		Collections.shuffle(list);
		return list.get(0);
	}

}

package com.capgemini.marketplace.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.marketplace.exception.NoStrategySetException;
import com.capgemini.marketplace.exception.NotOwnedStockReturnException;
import com.capgemini.marketplace.exception.TooPoorWalletException;
import com.capgemini.marketplace.strategy.BuyAndSellStrategy;

// TODO buy and sell interface. 
@Component
public class Gamer {
	
	@Autowired
	private StockBroker stockBrocker;
	
	@Autowired
	private StockExchange stockExchange;
	
	private CashWallet cashWallet;

	private StockWallet stockWallet;
		
	private BuyAndSellStrategy strategy;
	
	public Gamer() {
		setCashWallet(new CashWallet(10000));
		setStockWallet(new StockWallet());
	}
	
	public void play() throws NoStrategySetException{
		if(strategy == null)throw new NoStrategySetException();
		strategy.play(this);
	}
	
	public double getTotalEarnings() {
		return cashWallet.getCash() + stockWallet.totalValue();
	}
	
	public void buyStocks(Stock stock, int amount) throws TooPoorWalletException {
		double price = getStockBrocker().buyCost(stock, amount);
		getCashWallet().pay(price);
		getStockWallet().getBoughtStocks(stock, amount);
	}
	
	public void sellStocks(Stock stock, int amount) throws NotOwnedStockReturnException {
		double price = getStockBrocker().sellCost(stock, amount);
		getStockWallet().returnSoldStocks(stock, amount);
		getCashWallet().earn(price);
	}
	
	public StockBroker getStockBrocker() {
		return stockBrocker;
	}
	
	public void setStockBrocker(StockBroker stockBrocker) {
		this.stockBrocker = stockBrocker;
	}

	public CashWallet getCashWallet() {
		return cashWallet;
	}

	public StockWallet getStockWallet() {
		return stockWallet;
	}

	public void setCashWallet(CashWallet cashWallet) {
		this.cashWallet = cashWallet;
	}

	public void setStockWallet(StockWallet stockWallet) {
		this.stockWallet = stockWallet;
	}

	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}
	
	public void setStrategy(BuyAndSellStrategy buyAndSellStrategy) {
		this.strategy = buyAndSellStrategy;
	}
	
}

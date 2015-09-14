package com.capgemini.marketplace.model;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.marketplace.exception.NoStrategySetException;
import com.capgemini.marketplace.exception.NotOwnedStockReturnException;
import com.capgemini.marketplace.exception.TooPoorWalletException;

import junit.framework.Assert;

public class GamerTest {
	
	Gamer gamer;
	StockBroker stockBroker;
	double startCash;
	
	@Before
	public void setUp(){
		gamer = new Gamer();
		stockBroker = new StockBroker();
		gamer.setStockBrocker(stockBroker);
		startCash = 10000;
	}
	
	@Test(expected  = NoStrategySetException.class)
	public void testShouldThrowNoStrategySetException() throws NoStrategySetException {
		gamer.play();
	}
	
	@Test
	public void testGamerShouldHave10000CashAtStart() {
		Assert.assertNotNull(gamer.getCashWallet());
		Assert.assertEquals(gamer.getCashWallet().getCash(), 10000, 1);
	}
	
	@Test
	public void testGamerShouldHaveNotNullStockWallet() {
		Assert.assertNotNull(gamer.getStockWallet());
	}
	
	@Test
	public void testShouldCheckIfGamerBuyStocks() throws TooPoorWalletException{
		// given
		CashWallet cashWallet = new CashWallet(startCash);
		gamer.setCashWallet(cashWallet);
		Stock stock = new Stock();
		Date date = new Date(1, 1, 1500);
		stock.setHistory(Arrays.asList(new StockRate(date, 5)));
		
		// when
		gamer.buyStocks(stock, 5);
		
		// then
		assertEquals(startCash - 25 - 25*0.005, gamer.getCashWallet().getCash(), 0.01);
	}
	
	@Test
	public void testShouldCheckIfGamerSellStocks() throws NotOwnedStockReturnException{
		// given
		CashWallet cashWallet = new CashWallet(startCash);
		gamer.setCashWallet(cashWallet);
		Stock stock = new Stock();
		Date date = new Date(1, 1, 1500);
		stock.setHistory(Arrays.asList(new StockRate(date, 5)));
		gamer.getStockWallet().getShares().put(stock, 5);
		
		// when
		gamer.sellStocks(stock, 5);
		
		// then
		assertEquals(startCash + 25 - 25*0.005, gamer.getCashWallet().getCash(), 0.01);
	}
	
	
}

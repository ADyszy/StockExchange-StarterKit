package com.capgemini.marketplace.model;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.marketplace.exception.NotOwnedStockReturnException;

public class StockWalletTest {

	private StockWallet stockWallet;

	Stock stock;

	@Before
	public void setUp() {
		stockWallet = new StockWallet();
	}

	@Test
	public void testShouldCheckIfStocksAreBoughtCorrectly() {
		// given
		int amount = 200;
		stock = new Stock(Arrays.asList(new StockRate(null, 30)));
		stock.setName("STCK");

		// when
		stockWallet.getBoughtStocks(stock, amount);

		// then
		assertEquals(200, stockWallet.getAmountOfOwnedStock("STCK"));
	}

	@Test
	public void testShouldCheckIfStocksAreSelledCorrectly() throws NotOwnedStockReturnException {
		// given
		int amount = 200;
		stock = new Stock(Arrays.asList(new StockRate(null, 30)));
		stock.setName("STCK");
		stockWallet.getBoughtStocks(stock, amount);

		// when
		stockWallet.returnSoldStocks(stock, 50);

		// then
		assertEquals(150, stockWallet.getAmountOfOwnedStock("STCK"));
	}

	@Test(expected = NotOwnedStockReturnException.class)
	public void testShouldCheckIfThrowsException() throws NotOwnedStockReturnException {
		// given
		int amount = 200;
		stock = new Stock(Arrays.asList(new StockRate(null, 30)));
		stock.setName("STCK");
		stockWallet.getBoughtStocks(stock, amount);

		// when
		stockWallet.returnSoldStocks(stock, 500);

		// then ..
	}

	public void testShouldCheckIfMultipleStocksAreBoughtCorrectly() throws NotOwnedStockReturnException {
		// given
		int amount = 200;
		stock = new Stock(Arrays.asList(new StockRate(null, 30)));
		stock.setName("STCK");
		stockWallet.getBoughtStocks(stock, amount);

		// when
		stockWallet.getBoughtStocks(stock, 250);

		// then
		assertEquals(250, stockWallet.getShares().get(stock).intValue());
		assertEquals(1, stockWallet.getShares().keySet().size());
	}

	@Test
	public void testShouldCalculatetHeTotalValueOfStockWallet() {
		// given
		StockBroker stockBroker = new StockBroker();
		int amount = 200;
		stock = new Stock(Arrays.asList(new StockRate(null, 30)));
		stock.setName("STCK");
		stockWallet.getBoughtStocks(stock, amount);

		// when
		double val = stockWallet.totalValue(stockBroker);

		// then
		assertEquals(5970, val, 0.1);
	}

	@Test
	public void testShouldAddNewStockInstWhenNotExist() {
		// given
		int amount = 200;
		stock = new Stock(Arrays.asList(new StockRate(null, 30)));
		stock.setName("STCK");
		stockWallet.getBoughtStocks(stock, amount);
		
		// when
		Stock stock2 = new Stock(Arrays.asList(new StockRate(null, 30)));
		stock2.setName("STCK");
		stockWallet.getBoughtStocks(stock2, amount);

		// then
		Stock tmp = stockWallet.getStockByName("STCK");
		assertEquals(400, stockWallet.getShares().get(tmp).intValue());
		assertEquals(1, stockWallet.getShares().keySet().size());
	}

}

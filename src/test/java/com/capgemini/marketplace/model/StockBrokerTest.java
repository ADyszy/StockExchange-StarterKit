package com.capgemini.marketplace.model;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class StockBrokerTest {

	StockBroker stockBroker;
	
	@Before
    public void setUp() {
		stockBroker = new StockBroker();
    }
	
	@Test
	public void testShouldRoundBuyAndSellCostsProperly() {
		// given
		Stock stock = new Stock();
		// when
		stock.setHistory(Arrays.asList(new StockRate(new Date(1, 1, 2000), 94.4)));
		// then
		assertEquals(474.36, stockBroker.buyCost(stock, 5), 0.01);
		assertEquals(469.64, stockBroker.sellCost(stock, 5), 0.01);
	}

}

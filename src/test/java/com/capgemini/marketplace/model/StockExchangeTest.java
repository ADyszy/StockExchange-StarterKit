package com.capgemini.marketplace.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import com.capgemini.marketplace.data.DataService;

public class StockExchangeTest {
	
	DataService dataService; 

	StockExchange stockExchange = new StockExchange();
	
	@Before
    public void setUp() {
        dataService = new DataService();
        Whitebox.setInternalState(stockExchange, "dataService", dataService);
    }
	
	@Test
	public void testShouldLoadStocksFromTheFirstDay() {
		// given
		// when
		stockExchange.goToNewDay();
		stockExchange.updateStocks();
		// then
		assertEquals(1, stockExchange.getStocks().get(0).getHistory().size());
		assertEquals(0, stockExchange.getStocks().get(0).getHistory().get(0).getDate().compareTo(new Date(2, 1, 2013)));
	}

	@Test
	public void testShouldLoadStocksFromTheFifthDay() {
		// given
		for (int i=0; i<5; i++)
			stockExchange.goToNewDay();
		// when
		stockExchange.updateStocks();
		// then
		assertEquals(5, stockExchange.getStocks().get(0).getHistory().size());
		assertEquals(0, stockExchange.getStocks().get(4).getHistory().get(4).getDate().compareTo(new Date(8, 1, 2013)));
	}
	
	@Test
	public void testShouldEndSimulationAfterLastDayLoaded(){
		// when
		while(stockExchange.goToNewDay()) {
			stockExchange.updateStocks();
			// check if not null all the simulation time.
			assertNotNull(stockExchange.getStocks());
		}
		// then
		assertTrue(stockExchange.simulationEnded());
	}

}

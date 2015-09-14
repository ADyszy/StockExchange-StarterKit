package com.capgemini.marketplace.data;

import java.util.List;

import org.junit.Test;

import com.capgemini.marketplace.model.Stock;

import junit.framework.TestCase;

public class DataServiceTest extends TestCase {
	DataService dataService = new DataService();
	
	@Test
	public void testShouldLoadFiveStocksFromFile() {
		// given
		List<Stock> stockList = dataService.getActuallStocks(10);
		//then
		assertEquals(stockList.size(), 5); 
	}
	
	@Test
	public void testShouldGetActuallStocks() {
		// given
		List<Stock> stockList = dataService.getActuallStocks(10);
		// then
		assertEquals(5, stockList.size());
	}
 
	@Test
	public void testShouldLoadStockForKGHM() {
		// given
		List<Stock> stockList = dataService.getActuallStocks(10);
		//then
		assertTrue(containsCompany(stockList, "KGHM"));
	}
	 
	private boolean containsCompany(List<Stock> stockList, String companyName) {
		for (Stock stock : stockList)
			if (stock.getName().equals(companyName)) return true;
		return false;
	}
	
	@Test
	public void testShouldLoadStockHistoryAllCompanies() {
		// given
		List<Stock> stockList = dataService.getActuallStocks(10);
		//then
		assertTrue(allHaveHistory(stockList));
	}
	
	@Test
	public void testStockShouldHaveWholeHistory() {
		// given
		List<Stock> stockList = dataService.getActuallStocks(10);
		//then
		assertTrue(stockList.get(0).getHistory().size() == 10);
	}
	
	@Test
	public void testShouldSortStockByDate() {
		// given
		List<Stock> stockList = dataService.getActuallStocks(10);
		//then
		assertTrue(stockList.get(0).getHistory().size() == 10);
	}
	
	@Test
	public void testShouldReturnNullBecauseOfTooManyDays() {
		// given //when
		//then
		assertNull(dataService.getActuallStocks(9999999));
	}
	
	private boolean allHaveHistory(List<Stock> stockList) {
		for (Stock stock : stockList) {
			if(stock.getHistory() == null ||stock.getHistory().size() == 0)
				return false;
		}
		return true;
	}	
}

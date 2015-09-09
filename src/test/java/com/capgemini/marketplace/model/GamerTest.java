package com.capgemini.marketplace.model;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.marketplace.exception.NoStrategySetException;

import junit.framework.Assert;

public class GamerTest {
	
	Gamer gamer;
	
	@Before
	public void setUp(){
		gamer = new Gamer();
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
	
	
}

package com.capgemini.marketplace.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.marketplace.exception.TooPoorWalletException;

public class CashWalletTest {
	
	CashWallet cashWallet;
	
	@Before
	public void setUp(){
		cashWallet = new CashWallet(10000);
	}
	
	@Test
	public void testShouldCheckIfPaymentIsCorrect() throws TooPoorWalletException {
		// given
		double price = 400;
		//when
		cashWallet.pay(price);
		//then
		assertEquals(9600, cashWallet.getCash(), 0.01);
	}
	
	@Test(expected = TooPoorWalletException.class)
	public void testShouldThrowException() throws TooPoorWalletException{
		// given
		double price = 20000;
		// when
		cashWallet.pay(price);
		// then ...
	}
	
	@Test
	public void testShouldEarnMoneyCorrectly() {
		// given
		double price = 200;
		// when
		cashWallet.earn(price);
		// then
		assertEquals(10200, cashWallet.getCash(), 0.01);
	}

}

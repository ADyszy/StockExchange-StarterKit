package com.capgemini.marketplace;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.marketplace.exception.NoStrategySetException;
import com.capgemini.marketplace.model.Gamer;
import com.capgemini.marketplace.model.StockExchange;
import com.capgemini.marketplace.strategy.impl.StupidStrategy;

public class App {
	
	public static double runSimulation(ApplicationContext context) throws NoStrategySetException{
		Gamer gamer = (Gamer) context.getBean("gamer");
		gamer.setStrategy(new StupidStrategy());
		StockExchange stockExchange = (StockExchange) context.getBean("stockExchange");
		do {
			gamer.play();
		}while (stockExchange.updateStocks());
		return gamer.getTotalEarnings();
	}
	
	public static void main(String[] args) throws NoStrategySetException {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		System.out.println("total earnings: " + runSimulation(context));
	}
} 
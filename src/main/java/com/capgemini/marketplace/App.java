package com.capgemini.marketplace;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.marketplace.calculation.StockCalculations;
import com.capgemini.marketplace.exception.NoStrategySetException;
import com.capgemini.marketplace.model.Gamer;
import com.capgemini.marketplace.model.StockExchange;
import com.capgemini.marketplace.strategy.impl.PredictionBasedStrategy;
import com.capgemini.marketplace.strategy.impl.StupidRandomStrategy;

public class App {
	
	public static double runSimulation(ApplicationContext context) throws NoStrategySetException{
		Gamer gamer = (Gamer) context.getBean("gamer");
		gamer.setStrategy(new StupidRandomStrategy(gamer));
		StockExchange stockExchange = (StockExchange) context.getBean("stockExchange");
		do {
			gamer.play();
		}while (stockExchange.updateStocks() && !gamer.isBroke());
		System.out.println(gamer);
		return StockCalculations.round(gamer.getTotalEarnings(), 2);
	}
	
	public static void main(String[] args) throws NoStrategySetException {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		System.out.println("total earnings: " + runSimulation(context));
	} 
} 
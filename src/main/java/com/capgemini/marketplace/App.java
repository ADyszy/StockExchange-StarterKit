package com.capgemini.marketplace;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.marketplace.calculation.StockCalculations;
import com.capgemini.marketplace.exception.NoStrategySetException;
import com.capgemini.marketplace.model.Gamer;
import com.capgemini.marketplace.model.StockExchange;
import com.capgemini.marketplace.strategy.impl.StupidRandomStrategy;

public class App {
	
	private ApplicationContext context;
	private Gamer gamer;
	private StockExchange stockExchange;
	
	public App() {
		context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	}
	
	private void gamerSetUp(){
		gamer = (Gamer) context.getBean("gamer");
		gamer.setStrategy(new StupidRandomStrategy(gamer));
	}
	
	private void stockExchangeSetUp(){
		stockExchange = (StockExchange) context.getBean("stockExchange");
	}
	
	private double start() throws NoStrategySetException {
		do {
			gamer.play();
		}while (stockExchange.updateStocks() && !gamer.isBroke());
		System.out.println(gamer);
		return StockCalculations.round(gamer.getTotalEarnings(), 2);
	}

	public double runSimulation() throws NoStrategySetException{
		gamerSetUp();
		stockExchangeSetUp();
		return start();
	} 
	
	public static void main(String[] args) throws NoStrategySetException {
		App app = new App();
		System.out.println("total earnings: " + app.runSimulation());
	} 
} 
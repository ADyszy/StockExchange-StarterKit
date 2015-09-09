package com.capgemini.marketplace;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.marketplace.model.Gamer;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
//		Gamer gamer = (Gamer) context.getBean("gamer");
//		gamer.getStockBrocker().setName("alalala");
//		System.out.println(gamer.getStockBrocker().getName());
	}
} 
package com.capgemini.marketplace.spring.testbean;



public class TestBean {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public void printHello() {
		System.out.println("result: " + name);
	}
}

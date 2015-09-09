package com.capgemini.marketplace.spring.testbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfig {
	
	@Bean
	public TestBean testBean() {
		return new TestBean();
	}
	
}

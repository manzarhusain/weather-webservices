package com.sapient.impl;

import org.springframework.stereotype.Component;

@Component
public class WeatherServiceImpl extends BaseImpl {
	
	public void getWeatherByCity() {
		
		System.out.println("***** Rest URL:"+ this.environmentProperties.getWeatherRestUrl());
		
	}

}

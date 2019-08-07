package com.sapient.steps;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapient.impl.WeatherServiceImpl;

import io.cucumber.java.en.Given;
//import cucumber.api.java.en.Given; // this is deprecated

public class WeatherStepDefination extends BaseStep{
	
	@Autowired
	WeatherServiceImpl weatherServiceImpl;
	
	
	@Given("This is {int} step")
	public void step1(int number) {
		System.out.println("****step "+ number);
	}
	
	
	@Given("system checks the weather details of {string} and save resposne as {string}")
	public void cgecks(String city,String file) {
		System.out.println("***verifying the weather details of city:"+ city);
		weatherServiceImpl.getWeatherByCity();
	}

}

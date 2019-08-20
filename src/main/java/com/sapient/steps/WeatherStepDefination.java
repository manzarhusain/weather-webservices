package com.sapient.steps;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sapient.impl.WeatherServiceImpl;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
//import cucumber.api.java.en.Given; // this is deprecated
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class WeatherStepDefination extends BaseStep{
	
	@Autowired
	WeatherServiceImpl weatherServiceImpl;

	
	//WeatherDemoService.feature

	@Given("This is baseuri {string} and basepath {string}")
	public void this_is_baseuri_and_basepath(String baseuri, String basepath) {
	    System.out.println("***Setting configure");
	    weatherServiceImpl.configure(baseuri, basepath);
	    
	}

	@When("system hits hyderabad city end point url {string} and status code {int}")
	public void system_hits_hyderabad_city_url_and_status_code(String endpointurl, int statuscode) {
	    System.out.println("***Hitting demo weather url for Hyderabad city and verifying status code");
	    RequestSpecification requestspecification=weatherServiceImpl.getRequestSpecification();
	    weatherServiceImpl.getResponse(requestspecification, endpointurl, statuscode);
	    
	
	}

	@Then("verify city equals to {string}")
	public void verify_city_equals_to(String expectedcity) {
		System.out.println("***Verifying city equals to Hyderabad");
		weatherServiceImpl.verifycity(expectedcity);

	}
	
     
	
	//WeatherDemoServiceValidationThroughDB.feature
	
	@Given("This is {int} step")
	public void step1(int number) {
		System.out.println("****step "+ number);
	}
	
	
	@When("system hits hyderabad city url")
	public void system_hits_hyderabad_city_url() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("***System hitting Hyderabad url");
	
		weatherServiceImpl.getrequestcity();

	}
	
		
	@Then("verify status code {int} and save data from db into {string} and verify city equals to City in db")
	public void verify_status_code_and_save_data_from_db_into_and_verify_city_equals_to_City_in_db(int expectedstatuscode , String weatherdbjsonname) throws JsonGenerationException, JsonMappingException, IOException {
		  System.out.println("***Verifying status code 200 and city equal to present in db");
			
		  System.out.println("***Verifying status code 200");
		  weatherServiceImpl.verifystatuscode( expectedstatuscode);
			
		  System.out.println("***Verifying expected city Hyderabad from db");
		  weatherServiceImpl.verifycitybydb(weatherdbjsonname); 
	}
	
	
		
	//WeatherOpenapi.feature
	
	@Given("This is baseuri")
	public void this_is_baseuri() {
		
	   System.out.println("***Setting base uri");
       weatherServiceImpl.setBaseURI();
	}

	@When("system hits api url {string}")
	public void system_hits_url(String apiurl) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		System.out.println("***Setting api url");	
		System.out.println("############## apiurl"+ apiurl);
		weatherServiceImpl.getrequest(apiurl);
	  
	}

	@Then("verify following cod,name,country,pressure")
	public void verify_following_statuscode_name_country_pressure(DataTable dataTable) throws InterruptedException {
		
		System.out.println("***Verifying response");
		Thread.sleep(1000);
		weatherServiceImpl.VerifyResponseAttribute(dataTable);


	}


}

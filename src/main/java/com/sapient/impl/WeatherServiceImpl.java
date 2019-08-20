package com.sapient.impl;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.cassandra.utils.OutputHandler.SystemOutput;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.CassandraConnect.Cassandradbconnect;
import com.sapient.pojo.OpenWeather;
import com.sapient.pojo.Weather;
import com.sapient.pojo.WeatherDemo;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;



@Component
public class WeatherServiceImpl extends BaseImpl {
	
	
	//Global Setup Variables
	RequestSpecification httpRequest;
	RequestSpecBuilder build;

	public Response response;
	String responseasstring;
	
	WeatherDemo actualweather;
	WeatherDemo requestweather;
	
	public String path;
	public String jsonPathTerm;
	JsonPath jsonpatheval;
	ObjectMapper mapper;
	
	Cassandradbconnect dbcon;
	WeatherDemo weatherbydbdata;
	WeatherDemo weatherbydbjson;
	WeatherDemo expectedweatherbydbjsondata;
	
	OpenWeather openweatherobj;
	
	
	//configure baseuri and basepath
	public void configure(String baseuri, String basepath){
		
		RestAssured.baseURI=baseuri;
		RestAssured.basePath=basepath;
	}
	
	//setting request specification
	public RequestSpecification getRequestSpecification(){
		
		return RestAssured.given().contentType(ContentType.JSON);
	}
	
	
	//getting response
	public void getResponse(RequestSpecification requestSpecification, String endpointurl , int statuscode){
		
		response= requestSpecification.get(endpointurl);
		assertEquals(response.getStatusCode(), statuscode);
		response.then().log().all();
		
		//return response;
				
		
	}
	
	
	   
   //verify city
   public void verifycity(String expectedcity ){
	    System.out.println(response.then().log().all());
	    response.then().body("City",equalTo(expectedcity));
	    System.out.println("expectedcity:"+expectedcity);
		
		//deserialization through jackson api
		/*
		responseasstring=response.asString();
		System.out.println("Response as string: "+response.asString());		
		actualweather=convertjsontopojo(responseasstring);
		*/
		
		//deserialization through rest assured 
		actualweather=response.as(WeatherDemo.class);		
		System.out.println(actualweather.toString());
		System.out.println("actualweather.getCity(): "+ actualweather.getCity());
		
		//validation through object concept								
		assertEquals(actualweather.getCity(),expectedcity);
	   
   }

	
	
    //get request for specific city Hydrerabad
	public void getrequestcity() throws JsonParseException, JsonMappingException, IOException {
		
		System.out.println("***** Rest URL:"+this.environmentProperties.getWeatherRestUrlHyderabad());
		
		response= RestAssured.get(this.environmentProperties.getWeatherRestUrlHyderabad());
		System.out.println("Pretty response "+response.prettyPrint());

	
	}
	

	   //verify status code
	   public void verifystatuscode(int expectedstatuscode ){
		   
		   System.out.println("***Actual Status code ="+response.getStatusCode());
		   assertEquals("Status Check Failed!", expectedstatuscode, response.getStatusCode());
	   }
	   
	
	   
	   
	 //verify city from db
	   public void verifycitybydb(String weatherdbjsonname) throws JsonGenerationException, JsonMappingException, IOException{
		   
		   //***Make sure casssandra is up and running.Getting data from db cassandra
		   System.out.println("***Make sure casssandra is up and running.Getting data from db cassandra");
		   dbcon=new Cassandradbconnect();		   
		   weatherbydbdata=dbcon.getdbdata();
		   
		   //***Converting data from POJO into Json.Expected Json step. 
		   System.out.println("***Converting data from POJO into Json.Expected Json step. ");
		   convertpojodbtojson(weatherbydbdata,weatherdbjsonname);
		   System.out.println("***Expected json prepared is under ExpectedJson folder.");
		   
		   //***Converting data from Json into pojo to fetch expected data
		   System.out.println("***Converting data from Json into pojo to fetch expected data");
		   expectedweatherbydbjsondata= convertjsondbtopojo(weatherdbjsonname);
		   
		   //Actual data fetch below
		   actualweather=response.as(WeatherDemo.class);		
		   System.out.println(actualweather.toString());
		   System.out.println("Actual data fetch below");
		   System.out.println("actualweather.getCity(): "+ actualweather.getCity());
		   System.out.println("expectedweatherbydbjsondata.getCity(): "+expectedweatherbydbjsondata.getCity());
		   assertEquals(expectedweatherbydbjsondata.getCity(), actualweather.getCity());
		   
		   
	   }
	


	//set Base URI
    public void setBaseURI() {
    	System.out.println("***Setting base uri "+this.environmentProperties.getWeatherOpenServiceUrl());
        RestAssured.baseURI = this.environmentProperties.getWeatherOpenServiceUrl();
    }

    
    // get request and fetching response
    public void getrequest(String apiurl) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
        
    	System.out.println("***api url:"+apiurl);
    	System.out.println("***Response:");
    	//httpRequest=RestAssured.given().contentType(ContentType.JSON);
    	//System.out.println(this.environmentProperties.getWeatherOpenServiceUrl());

    	Thread.sleep(100);
    	response=RestAssured.get(apiurl);

		response.then().log().all();
		System.out.println("Pretty response "+response.prettyPrint());

	
	}

	
	
	//verifying response attributes
	public void VerifyResponseAttribute(DataTable datatable)
	{
		List<String> verifyattri=datatable.asList();
		
		//inline validation
		response.then().body("cod",equalTo(Integer.parseInt(verifyattri.get(0))));
		response.then().body("name", equalTo(verifyattri.get(1)));
		response.then().body("sys.country", equalTo(verifyattri.get(2)));
		response.then().body("main.pressure", equalTo(Integer.parseInt(verifyattri.get(3))));
	    
		//java objects validation
		openweatherobj=response.as(OpenWeather.class);
		
		System.out.println("Expected cod:"+Integer.parseInt(verifyattri.get(0)));
		System.out.println("Actual cod:"+openweatherobj.getCod());	
	    //assertEquals(verifyattri.get(0),openweatherobj.getCod());
	    
	    
	    System.out.println("Expected name:"+verifyattri.get(1));
	    System.out.println("Actual name:"+openweatherobj.getName());
	    assertEquals(verifyattri.get(1), openweatherobj.getName());
	    
	    
	    System.out.println("Expected country:"+verifyattri.get(2));
	    System.out.println("Actual country:"+openweatherobj.getSys().getCountry());
	    assertEquals(verifyattri.get(2), openweatherobj.getSys().getCountry());

	}
	
	
	   //converting pojo to json the db data
	    public void convertpojodbtojson(WeatherDemo weatherbydbdata, String weatherdbjsonname ) throws JsonGenerationException, JsonMappingException, IOException{
	    	
			   
			   mapper=new ObjectMapper();	
			   System.out.println(this.environmentProperties.getExpectedJsonFolderpath());
		       // Java objects to JSON file
		       mapper.writeValue(new File(this.environmentProperties.getExpectedJsonFolderpath()+weatherdbjsonname), weatherbydbdata);
						       	
	    }
	    
	    //converting jsondb to pojo
	    public WeatherDemo convertjsondbtopojo(String weatherdbjsonname) throws JsonParseException, JsonMappingException, IOException
	    {
	    	
	    	  mapper = new ObjectMapper();
			 
	 		  // JSON file to Java object
	    	  weatherbydbjson = mapper.readValue(new File(this.environmentProperties.getExpectedJsonFolderpath()+weatherdbjsonname), WeatherDemo.class);

	          // compact print
	          System.out.println("Jsonfile to java object :"+weatherbydbjson);

	          return weatherbydbjson; 
	    }
        

	    
	    
	    
	    //below just to describe serialization and deserialization
	    public WeatherDemo convertjsontopojo(String actualjsonstring) throws JsonParseException, JsonMappingException, IOException
	    {
	    	
	    	  mapper = new ObjectMapper();
			 
	 		// JSON file to Java object
	          //Weather weather1 = mapper.readValue(new File("C:\\Users\\risgoyal1\\MobileAutomation\\resttest\\ExpectedJsons\\Weather.json"), Weather.class);

	          // JSON string to Java object
	          //jsonstring we have to fetch after get request(whether we use rest assured or anything else)
	          //String actualjsonInString = "{\"weatherDescription\":\"scattered clouds\",\"windDirectionDegree\":\"180 Degree\",\"city\":\"Hyderabad\",\"humidity\":\"59 Percent\",\"windSpeed\":\"1.5 Km per hour\",\"temperature\":\"31.68 Degree celsius\"}";
	    	  WeatherDemo weatherjackson = mapper.readValue(actualjsonstring, WeatherDemo.class);

	          // compact print
	          System.out.println("Jsonstring to java object "+weatherjackson);

	          // pretty print
	          //String prettyweather = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(weather2);
	         // System.out.println(prettyweather);
	          return weatherjackson; 
	    }
	    
	    
	    // covert pojo to json
	    public void convertpojotojson() throws JsonGenerationException, JsonMappingException, IOException{
	    	
	    	
	    	requestweather = new WeatherDemo();
			
	    	requestweather.setCity("Hyderabad");
	    	requestweather.setHumidity("59 Percent");
	    	requestweather.setTemperature("31.68 Degree celsius");
	    	requestweather.setWeatherDescription("scattered clouds");
	    	requestweather.setWindSpeed("1.5 Km per hour");
	    	requestweather.setWindDirectionDegree("180 Degree");
			
			
			mapper = new ObjectMapper();

			 
		    // Java objects to JSON file
		    mapper.writeValue(new File(this.environmentProperties.getExpectedJsonFolderpath()+"\\Weather.json"), requestweather);

		    // Java objects to JSON string - compact-print
		    String jsonString = mapper.writeValueAsString(requestweather);

		    System.out.println(jsonString);

		    // Java objects to JSON string - pretty-print
		    String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestweather);

		    System.out.println(jsonInString2);
	    }
	    
		
	    //Sets base path
	    public void setapiURI(String apiUrl) {
	        RestAssured.basePath= apiUrl;
	    }
	   
	  
	    //Reset Base URI (after test)
	    public void resetBaseURI() {
	        RestAssured.baseURI = null;
	    }

	    //Reset base path(after test)
	    public void resetBasePath() {
	        RestAssured.basePath = null;
	    }
	    


	    //Sets ContentType
	    public void setContentType(ContentType Type) {
	        given().contentType(Type);
	    }

	    //Sets Json path term
	    public void setJsonPathTerm(String jsonPath) {
	        jsonPathTerm = jsonPath;
	    }

	    //Created search query path
	    public void createSearchQueryPath(String searchTerm, String param, String paramValue) {
	        path = searchTerm + "/" + jsonPathTerm + "?" + param + "=" + paramValue;
	    }


	    //Returns JsonPath object
	    public static JsonPath getJsonPath(Response res) {
	        String json = res.asString();
	        //System.out.print("returned json: " + json +"\n");
	        return new JsonPath(json);
	
		
	      }  
	    
	    //Returns response
	   // public static Response getResponse() {
	        //System.out.print("path: " + path +"\n");
	  // return get(path);
	  //  }
	    
	    
	    
	   


}

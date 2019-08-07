 @WeatherService 
Feature: Verify the Weather Details of the city

@WeatherService_1 
Scenario: Validation the weather details of City 
	Given This is 1 step
	Given system checks the weather details of "Dehi, India" and save resposne as "WeatherServiceResponse.json"
	
	@WeatherService_2 
Scenario Outline: Validation the weather details of City 
	
	Given system checks the weather details of <City> and save resposne as "WeatherServiceResponse.json"
	
	Examples:
	|City|
	| "Delhi, India"|
	| "Noida, India"|
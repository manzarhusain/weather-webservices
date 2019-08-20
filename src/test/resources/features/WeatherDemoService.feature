 @WeatherService 
Feature: Verify the Weather Details of the city


@WeatherService_1 
Scenario: Validation the weather details of City Hyderabad
	Given This is baseuri "http://restapi.demoqa.com" and basepath "/utilities/weather/city"
	When system hits hyderabad city end point url "/Hyderabad" and status code 200
	Then verify city equals to "Hyderabad"
	

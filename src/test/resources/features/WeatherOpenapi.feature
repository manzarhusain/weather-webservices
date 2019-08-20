 @WeatherService 
Feature: Verify the Weather Details of the city London


@WeatherService_3
Scenario: Validation the weather details of City London
	Given This is baseuri 
	When system hits api url "/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22"
	Then verify following cod,name,country,pressure
	|200|London|GB|1012|
	

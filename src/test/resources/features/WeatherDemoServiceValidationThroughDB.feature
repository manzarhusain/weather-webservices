 @WeatherService 
Feature: Verify the Weather Details of the city and validation through data from DB


@WeatherService_2 
Scenario: Validation the weather details of City Hyderabad from DB data
	Given This is 1 step
	When system hits hyderabad city url
	Then verify status code 200 and save data from db into "weatherbydbdata.json" and verify city equals to City in db
	

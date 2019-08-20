package com.sapient.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "html:target/cucumber-htmlreport",
				//json -pretty is chnaged to json in latest version
		"json:target/cucumber-report.json" },
		features = { "src/test/resources/features" },
		glue={"com.sapient.steps"},
		dryRun = false,
		tags="@WeatherService_2,@WeatherService_3",
		strict=true,
		monochrome=true
		)


public class RunCucumberTest {
	
	

}

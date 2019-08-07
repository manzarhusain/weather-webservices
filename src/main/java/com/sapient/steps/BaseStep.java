package com.sapient.steps;

import org.springframework.test.context.ContextConfiguration;

//Any class extending this class can autowire components.

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public abstract class BaseStep {

}

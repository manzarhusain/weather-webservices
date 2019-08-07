package com.sapient.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.libs.EnvironmentProperties;

@Component
public abstract class BaseImpl {
	
	@Autowired
	public EnvironmentProperties environmentProperties;

}

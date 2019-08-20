package com.sapient.libs;

public class EnvironmentProperties {
	
	private String weatherRestUrl;
	private String weatherServiceAppKey;
	private String weatherRestUrlHyderabad;
	private String weatherOpenServiceUrl;
	
	public final static String Cassandra_ServerIp ="127.0.0.1";
	public final static String Cassandra_KeySpace ="weatherspace";
	public final static String Cassandra_table1 ="weather";
	
	String workingDir = System.getProperty("user.dir");
	String ExpectedJsonFolderpath = workingDir+"\\ExpectedJson\\";
	



	public String getWeatherOpenServiceUrl() {
		return weatherOpenServiceUrl;
	}
	public void setWeatherOpenServiceUrl(String weatherOpenServiceUrl) {
		this.weatherOpenServiceUrl = weatherOpenServiceUrl;
	}
	
	
	
	public String getExpectedJsonFolderpath() {
		return ExpectedJsonFolderpath;
	}
	public void setExpectedJsonFolderpath(String expectedJsonFolderpath) {
		ExpectedJsonFolderpath = expectedJsonFolderpath;
	}
	public String getWorkingDir() {
		return workingDir;
	}
	public void setWorkingDir(String workingDir) {
		this.workingDir = workingDir;
	}
	public String getWeatherRestUrl() {
		return weatherRestUrl;
	}
	public void setWeatherRestUrl(String weatherRestUrl) {
		this.weatherRestUrl = weatherRestUrl;
	}
	public String getWeatherServiceAppKey() {
		return weatherServiceAppKey;
	}
	public void setWeatherServiceAppKey(String weatherServiceAppKey) {
		this.weatherServiceAppKey = weatherServiceAppKey;
	}
	public String getWeatherRestUrlHyderabad() {
		return weatherRestUrlHyderabad;
	}
	public void setWeatherRestUrlHyderabad(String weatherRestUrlHyderabad) {
		this.weatherRestUrlHyderabad = weatherRestUrlHyderabad;
	}

	}
	
	



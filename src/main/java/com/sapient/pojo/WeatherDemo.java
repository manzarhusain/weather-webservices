package com.sapient.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDemo {
	
	@Override
	public String toString() {
		return "WeatherDemo [City=" + City + ", Temperature=" + Temperature + ", Humidity=" + Humidity
				+ ", WeatherDescription=" + WeatherDescription + ", WindSpeed=" + WindSpeed + ", WindDirectionDegree="
				+ WindDirectionDegree + "]";
	}
	@JsonProperty("City")
	private String City;
	@JsonProperty("Temperature")
    private String Temperature;
	@JsonProperty("Humidity")
    private String Humidity;
	@JsonProperty("WeatherDescription")
    private String WeatherDescription;//weth_desc
	@JsonProperty("WindSpeed")
    private String WindSpeed;
	@JsonProperty("WindDirectionDegree")
    private String WindDirectionDegree;

    public void setCity(String City){
        this.City = City;
    }
    public String getCity(){
        return this.City;
    }
    public void setTemperature(String Temperature){
        this.Temperature = Temperature;
    }
    public String getTemperature(){
        return this.Temperature;
    }
    public void setHumidity(String Humidity){
        this.Humidity = Humidity;
    }
    public String getHumidity(){
        return this.Humidity;
    }
    public void setWeatherDescription(String WeatherDescription){
        this.WeatherDescription = WeatherDescription;
    }
    public String getWeatherDescription(){
        return this.WeatherDescription;
    }
    public void setWindSpeed(String WindSpeed){
        this.WindSpeed = WindSpeed;
    }
    public String getWindSpeed(){
        return this.WindSpeed;
    }
    public void setWindDirectionDegree(String WindDirectionDegree){
        this.WindDirectionDegree = WindDirectionDegree;
    }
    public String getWindDirectionDegree(){
        return this.WindDirectionDegree;
    }


}

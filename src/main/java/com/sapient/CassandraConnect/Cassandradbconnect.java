package com.sapient.CassandraConnect;

import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import com.sapient.pojo.Weather;
import com.sapient.pojo.WeatherDemo;

@Component
public class Cassandradbconnect {


	    //public static void main(String[] args) 
	    //{
	        String serverIp = "127.0.0.1";
	        String keyspace = "weatherspace";
	        String dbresp;
	        
	        String cityvalue;
	        String Temperature;
	        String Humidity;
	        String WeatherDescription;
	        String WindSpeed;
	        String WindDirectionDegree;
	        
	        WeatherDemo weatherbydb=new WeatherDemo();
	        
            
	        public WeatherDemo getdbdata()
	        {
	        	
	        Cluster cluster = Cluster.builder()
	                .addContactPoints(serverIp)
	                .build();

	        Session session = cluster.connect(keyspace);

	        String cqlStatement = "SELECT * FROM weather";
	        
	        for (Row row : session.execute(cqlStatement)) {
	        	dbresp=row.toString();
	            System.out.println(dbresp);
	            System.out.println(row);
	           
	            cityvalue=row.getString("city");
	            System.out.println(cityvalue); 
	            Temperature	=row.getString("temperature");
	            Humidity=row.getString("humidity");
	            WeatherDescription=row.getString("weatherdescription");
	            WindSpeed=row.getString("windspeed");
	            WindDirectionDegree=row.getString("winddirectiondegree");

	        }
	   
           //Setting value to object fields 
	        weatherbydb.setCity(cityvalue);	         
	        weatherbydb.setHumidity(Humidity);
	        weatherbydb.setTemperature(Temperature);
	        weatherbydb.setWeatherDescription(WeatherDescription);
	        weatherbydb.setWindSpeed(WindSpeed);
	        weatherbydb.setWindDirectionDegree(WindDirectionDegree);
	         
	        session.close();
	        
	        return weatherbydb;
	              
	       
	        }      
	    
}






package com.qa.api.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class TestBase {

	
	public String readPropertyValue(String key) throws IOException {
		//"create object for Properties");
		Properties prop = new Properties();
		//"read the properties file"); 
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\api\\config\\config.properties");
			//"load all the properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	/*
	 * public static JsonPath rawToJson(Response response) { JsonPath js = new
	 * JsonPath(response.body().asString()); return js; }
	 */
}

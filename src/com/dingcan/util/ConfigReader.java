package com.dingcan.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigReader {
	public ConfigReader(){}
	private static Properties props = new Properties(); 
	static{
		try {
			props.load(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("apiconfig.properties"), "UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    }
}

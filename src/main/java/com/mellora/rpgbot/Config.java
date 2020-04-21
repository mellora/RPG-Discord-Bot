package com.mellora.rpgbot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Config {
	// Create Resource Object from resources folder
	private static final Resource res = new ClassPathResource("config/config.json");
	// Creates a JSON Object from the Resource Object
	private static JSONObject configJson;

	static {
		try {
			configJson = (JSONObject) new JSONParser().parse(new FileReader(res.getFile()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		return (String) configJson.get(key);
	}
}

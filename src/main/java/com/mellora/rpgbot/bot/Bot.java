package com.mellora.rpgbot.bot;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.JDABuilder;

@Component
public class Bot {
	private String token;

	public Bot() throws Exception {
		// Create Resource Object from resources folder
		Resource res = new ClassPathResource("config/config.json");
		// Creates a JSON Object from the Resource Object
		JSONObject jsonObj = (JSONObject) new JSONParser().parse(new FileReader(res.getFile()));
		// Assigns the bot token from the config file
		token = (String) jsonObj.get("token");
		// Initializes the Bot using the token provided.
		JDABuilder.createDefault(token).build();
	}
}

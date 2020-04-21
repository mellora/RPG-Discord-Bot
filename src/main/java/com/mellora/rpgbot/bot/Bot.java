package com.mellora.rpgbot.bot;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

@Component
public class Bot {
	private String token;

	public Bot() throws Exception {
		// Create Default Embed
		EmbedUtils.setEmbedBuilder(() -> new EmbedBuilder().setColor(0x43ff36).setFooter("The Cartographer"));
		
		// Create Resource Object from resources folder
		Resource res = new ClassPathResource("config/config.json");
		// Creates a JSON Object from the Resource Object
		JSONObject jsonObj = (JSONObject) new JSONParser().parse(new FileReader(res.getFile()));
		// Assigns the bot token from the config file
		token = (String) jsonObj.get("token");
		
		// Initializes the Bot using the token provided.
		JDABuilder.createDefault(token).setActivity(Activity.playing("With Your Lives")).build();
	}
}

package com.mellora.rpgbot.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mellora.rpgbot.service.Listener;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

@Component
public class DiscordBuilder {

	private String botToken;
	
	@Autowired
	Listener listener;

	public DiscordBuilder(@Value("${discord.bot.token}") String botToken) {
		this.botToken = botToken;
	}

	@PostConstruct
	private void setUp() throws Exception {
		// Initializes the Bot using the token provided.
		JDABuilder jda = JDABuilder.createDefault(botToken);
		// Adds the Event Listener to handle guild events.
		jda.addEventListeners(listener);
		// Sets the activity of the bot in connected guilds when online.
		jda.setActivity(Activity.playing("With Your Lives"));
		// Builds the instance of the bot for application runtime.
		jda.build();
	}
}

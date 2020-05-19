package com.mellora.rpgbot.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscordBuilder {
	
	private String botToken;
	
	public DiscordBuilder(@Value("${discord.bot.token}") String botToken) {
		this.botToken = botToken;
	}
	
	@PostConstruct
	private void setUp() {
		System.out.println(botToken);
	}
}

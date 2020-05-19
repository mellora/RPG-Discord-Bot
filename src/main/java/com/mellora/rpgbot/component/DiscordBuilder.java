package com.mellora.rpgbot.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscordBuilder {
	
	public DiscordBuilder(@Value("${discord.bot.token}") String botToken) {
		System.out.println(botToken);
	}
}

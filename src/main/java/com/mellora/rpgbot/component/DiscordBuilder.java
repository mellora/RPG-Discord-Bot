package com.mellora.rpgbot.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscordBuilder {

	@Value("${discord.bot.token}")
	private String botToken;
}

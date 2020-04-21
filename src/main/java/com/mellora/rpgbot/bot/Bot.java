package com.mellora.rpgbot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.JDABuilder;

@Component
public class Bot {
	@Value("${bot.token}")
	private String token;

	public Bot() throws Exception {
		System.out.println(token);
//		JDABuilder.createDefault(token).build();
	}
}

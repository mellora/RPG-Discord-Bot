package com.mellora.rpgbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mellora.rpgbot.bot.Bot;

@SpringBootApplication
public class DiscordJavaBotApplication {
	
	@Autowired
	Bot bot;

	public static ConfigurableApplicationContext ctx;
	public static void main(String[] args) {
		ctx = SpringApplication.run(DiscordJavaBotApplication.class, args);
	}

}

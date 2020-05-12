package com.mellora.rpgbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import com.mellora.rpgbot.bot.Bot;


/*
 * Main Class.
 * created the ConfigurableApplicationContext as a public static to
 * gracefully shutdown the program when a shutdown command is called.
 */
@SpringBootApplication
@EnableCaching
public class DiscordJavaBotApplication {
	
	@Autowired
	Bot bot;
	
	public static ConfigurableApplicationContext ctx;
	
	public static void main(String[] args) {
		ctx = SpringApplication.run(DiscordJavaBotApplication.class, args);
	}

}

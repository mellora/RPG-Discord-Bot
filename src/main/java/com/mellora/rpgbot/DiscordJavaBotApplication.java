package com.mellora.rpgbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mellora.rpgbot.bot.Bot;

@SpringBootApplication
public class DiscordJavaBotApplication {
	@Autowired
	Bot bot;
	public static void main(String[] args) {
		SpringApplication.run(DiscordJavaBotApplication.class, args);
	}

}

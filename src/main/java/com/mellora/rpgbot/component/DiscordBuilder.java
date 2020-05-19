package com.mellora.rpgbot.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mellora.rpgbot.service.dice.DiceRoller;

@Component
public class DiscordBuilder {

	@Autowired
	DiceRoller roller;
	
	public DiscordBuilder(@Value("${discord.bot.token}") String botToken) {
		
	}
	
	@PostConstruct
	private void print() {
		System.out.println(roller.rollD4());
	}
}

package com.mellora.rpgbot.bot;

import org.springframework.stereotype.Component;

import com.mellora.rpgbot.Config;

import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

@Component
public class Bot {

	public Bot() throws Exception {
		// Create Default Embed
		EmbedUtils.setEmbedBuilder(() -> new EmbedBuilder().setColor(0x43ff36).setFooter("The Cartographer"));

		String token = Config.get("token");

		// Initializes the Bot using the token provided.
		JDABuilder.createDefault(token).setActivity(Activity.playing("With Your Lives")).build();
	}
}

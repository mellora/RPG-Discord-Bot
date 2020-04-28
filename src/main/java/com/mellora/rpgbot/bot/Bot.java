package com.mellora.rpgbot.bot;

import org.springframework.stereotype.Component;

import com.mellora.rpgbot.Config;

import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

/*
 * Class that handles bot initialization.
 */
@Component
public class Bot {

	public Bot() throws Exception {
		// Create Default Embed
		EmbedUtils.setEmbedBuilder(() -> new EmbedBuilder().setColor(0x43ff36).setFooter("The Cartographer"));

		// Gets Discord bot token from the config.json
		String token = Config.get("token");

		// Initializes the Bot using the token provided.
		JDABuilder.createDefault(token).addEventListeners(new Listener()).setActivity(Activity.playing("With Your Lives")).build();
	}
}

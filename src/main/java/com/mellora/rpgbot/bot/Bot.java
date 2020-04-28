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
		JDABuilder jda = JDABuilder.createDefault(token);
		// Adds the Event Listener to handle guild events.
		jda.addEventListeners(new Listener());
		// Sets the activity of the bot in connected guilds when online.
		jda.setActivity(Activity.playing("With Your Lives"));
		// Builds the instance of the bot for application runtime.
		jda.build();
	}
}

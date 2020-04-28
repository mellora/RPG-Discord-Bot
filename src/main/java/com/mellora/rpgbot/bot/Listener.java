package com.mellora.rpgbot.bot;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import com.mellora.rpgbot.Config;
import com.mellora.rpgbot.DiscordJavaBotApplication;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.MessageActivity.Application;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
 * This class creates a listener used by the bot class to register when a command is
 * sent within discord.
 */
public class Listener extends ListenerAdapter {

	// Allows this class to use the logger set up by Spring Boot.
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	// Creates an instance of the manager class to handle commands.
	private final CommandManager manager = new CommandManager();

	// Method prints to logger when the bot is ready on Discord.
	@Override
	public void onReady(@Nonnull ReadyEvent event) {
		LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
	}

	// Method fires when an event is registered on a Discord server.
	@Override
	public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
		// Creates a user object to help with figuring out if the bot needs to do anything.
		User user = event.getAuthor();

		/*
		 *  Checks if the event creator us a bot account or a webhook message.
		 *  This prevents the bot from spending time on unnecessary work by
		 *  returning from method call.
		 */
		if (user.isBot() || event.isWebhookMessage()) {
			return;
		}

		// Gets the prefix from config file
		String prefix = Config.get("default_prefix");  // Work on switching to database
		// Gets the content of the event message
		String raw = event.getMessage().getContentRaw();

		/*
		 * Checks if the message has the correct guild prefix and sent a shutdown command.
		 * Then checks if the user is the owner before running code block.
		 */
		if (raw.equalsIgnoreCase(prefix + "shutdown") && user.getId().equals(Config.get("owner_id"))) {
			// Sends shutdown message to the channel that spawned the event.
			event.getChannel().sendMessage("Shutting Down.").queue();
			// Sends to logger shutdown message.
			LOGGER.info("Shutting Down");
			// Sends shutdown command to the Discord bot instance.
			event.getJDA().shutdown();
			// Sends kill command to application bot threads.
			BotCommons.shutdown(event.getJDA());
			// Send exit command to Spring Application using the public context.
			SpringApplication.exit(DiscordJavaBotApplication.ctx, () -> 0);
			return;
		}

		// Checks if message has correct guild prefix and sends command to manager if it does.
		if (raw.startsWith(prefix)) {
			manager.handle(event);
		}
	}
}

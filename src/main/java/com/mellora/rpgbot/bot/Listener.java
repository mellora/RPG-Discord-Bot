package com.mellora.rpgbot.bot;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import com.mellora.rpgbot.Config;
import com.mellora.rpgbot.DiscordJavaBotApplication;
import com.mellora.rpgbot.dao.GuildSettingsRepository;

import lombok.extern.slf4j.Slf4j;
import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
 * This class creates a listener used by the bot class to register when a command is
 * sent within discord.
 */
@Slf4j
public class Listener extends ListenerAdapter {

	@Autowired
	private GuildSettingsRepository repo;
	
	// Creates an instance of the manager class to handle commands.
	private final CommandManager manager = new CommandManager();

	// Method prints to logger when the bot is ready on Discord.
	@Override
	public void onReady(@Nonnull ReadyEvent event) {
		log.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
	}

	// Method fires when an event is registered on a Discord server.
	@Override
	public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
		// Creates a user object to help with figuring out if the bot needs to do
		// anything.
		User user = event.getAuthor();

		/*
		 * Checks if the event creator us a bot account or a webhook message. This
		 * prevents the bot from spending time on unnecessary work by returning from
		 * method call.
		 */
		if (user.isBot() || event.isWebhookMessage()) {
			return;
		}

		// Gets the prefix from config file
//		String prefix = Config.get("default_prefix"); // Work on switching to database
		long guildId = event.getGuild().getIdLong();
		String prefix = getPrefix(guildId);
		// Gets the content of the event message
		String raw = event.getMessage().getContentRaw();

		/*
		 * Checks if the message has the correct guild prefix and sent a shutdown
		 * command. Then checks if the user is the owner before running code block.
		 */
		if (raw.equalsIgnoreCase(prefix + "shutdown") && user.getId().equals(Config.get("owner_id"))) {
			// Sends shutdown message to the channel that spawned the event.
			event.getChannel().sendMessage("Shutting Down.").queue();
			// Sends to logger shutdown message.
			log.info("Shutting Down");
			// Sends shutdown command to the Discord bot instance.
			event.getJDA().shutdown();
			// Sends kill command to application bot threads.
			BotCommons.shutdown(event.getJDA());
			// Send exit command to Spring Application using the public context.
			SpringApplication.exit(DiscordJavaBotApplication.ctx, () -> 0);
			return;
		}

		// Checks if message has correct guild prefix and sends command to manager if it
		// does.
		if (raw.startsWith(prefix)) {
			manager.handle(event);
		}
	}

	private String getPrefix(long guildId) {
//		if(repo.findIfGuildExists(guildId)) {
//			return repo.getByGuildId(guildId).getPrefix();
//		}else {
//			repo.save(new GuildSettings(guildId));
//			return Config.get("default_prefix");
//		}
		return Config.get("default_prefix");
	}
}

package com.mellora.rpgbot.service;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mellora.rpgbot.dao.GuildSettingsRepository;
import com.mellora.rpgbot.dao.entities.GuildSettings;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service
@Slf4j
public class Listener extends ListenerAdapter {
	// Creates an instance of the manager class to handle commands.
	@Autowired
	private CommandManager manager;

	@Autowired
	private GuildSettingsRepository repo;

	private String prefix;

	public Listener(@Value("${discord.bot.prefix.default}") String prefix) {
		this.prefix = prefix;
	}

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

		// Gets the prefix from application.yml file
		final long guildId = event.getGuild().getIdLong();
		String prefix = getPrefix(guildId); // Work on switching to database
		// Gets the content of the event message
		String raw = event.getMessage().getContentRaw();

		// Checks if message has correct guild prefix and sends command to manager if it
		// does.
		if (raw.startsWith(prefix)) {
			manager.handle(event);
		}
	}

	private String getPrefix(long guildId) {
		GuildSettings guild = repo.getGuildByGuildId(guildId);
		if (guild != null) {
			return guild.getPrefix();
		} else {
			repo.save(new GuildSettings(null, guildId, prefix));
			return prefix;
		}
	}
}

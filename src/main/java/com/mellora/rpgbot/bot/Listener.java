package com.mellora.rpgbot.bot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Nonnull;

import org.springframework.boot.SpringApplication;

import com.mellora.rpgbot.Config;
import com.mellora.rpgbot.DiscordJavaBotApplication;
import com.mellora.rpgbot.dao.JdbcRepo;

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
		String prefix = getPrefix(event.getGuild().getIdLong());
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
		try (final PreparedStatement preparedStatement = JdbcRepo.jdbcTemplate.getDataSource().getConnection().prepareStatement("SELECT prefix FROM guild_settings WHERE guild_id = ?")) {
			preparedStatement.setString(1, String.valueOf(guildId));
			try (final ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.next()) {
					System.out.println(resultSet.getString("prefix"));
					return resultSet.getString("prefix");
				}
			}
			try (final PreparedStatement insertStatement = JdbcRepo.jdbcTemplate.getDataSource().getConnection().prepareStatement("INSERT INTO guild_settings (guild_id) VALUES (?)")){
				insertStatement.setString(1, String.valueOf(guildId));
				insertStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(Config.get("default_prefix"));
		return Config.get("default_prefix");
	}
}

package com.mellora.rpgbot.bot;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

import com.mellora.rpgbot.Config;
import com.mellora.rpgbot.DiscordJavaBotApplication;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.MessageActivity.Application;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	private final CommandManager manager = new CommandManager();
	
	@Override
	public void onReady(@Nonnull ReadyEvent event) {
		
	}
	
	@Override
	public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
		User user = event.getAuthor();
		
		if(user.isBot() || event.isWebhookMessage()) {
			return;
		}
		
		String prefix = Config.get("default_prefix");
		String raw = event.getMessage().getContentRaw();
		
		if(raw.equalsIgnoreCase(prefix + "shutdown") && user.getId().equals(Config.get("owner_id"))) {
			event.getChannel().sendMessage("Shutting Down.").queue();
			LOGGER.info("Shutting Down");
			event.getJDA().shutdown();
			BotCommons.shutdown(event.getJDA());
			SpringApplication.exit(DiscordJavaBotApplication.ctx, () -> 0);
			return;
		}
		
		if(raw.startsWith(prefix)) {
			manager.handle(event);
		}
	}
}

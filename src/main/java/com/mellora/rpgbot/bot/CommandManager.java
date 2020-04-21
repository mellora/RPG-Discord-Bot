package com.mellora.rpgbot.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import com.mellora.rpgbot.bot.command.CommandContext;
import com.mellora.rpgbot.bot.command.ICommand;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandManager {

	private final List<ICommand> commands = new ArrayList<>(); // Look to use map instead

	public CommandManager() {

	}

	private void addCommand(ICommand cmd) {
		boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));
		if (nameFound) {
			throw new IllegalArgumentException("A Command with this name is allready present");
		}
		commands.add(cmd);
	}

	@Nullable
	public ICommand getCommand(String search) {
		String searchLower = search.toLowerCase();
		for (ICommand cmd : this.commands) {
			if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
				return cmd;
			}
		}
		return null;
	}
	
	void handle(GuildMessageReceivedEvent event) {
		String[] split = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote("!!"), "").split("\\s+");
		String invoke = split[0].toLowerCase();
		ICommand cmd = this.getCommand(invoke);
		if(cmd != null) {
			event.getChannel().sendTyping().queue();
			List<String> args = Arrays.asList(split).subList(1, split.length);
			CommandContext ctx = new CommandContext(event, args);
			cmd.handle(ctx);
		}
	}
}
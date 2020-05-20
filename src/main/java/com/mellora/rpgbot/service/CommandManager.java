package com.mellora.rpgbot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mellora.rpgbot.service.command.CommandContext;
import com.mellora.rpgbot.service.command.ICommand;
import com.mellora.rpgbot.service.command.commands.HelpCommand;
import com.mellora.rpgbot.service.command.commands.RollCharacter4d6DropLowestCommand;
import com.mellora.rpgbot.service.command.commands.RollCharacter4d6Reroll1DropLowestCommand;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/*
 * Class handles command registration and handles events sent by the listiner class.
 */
@Service
public class CommandManager {

	// Holds implemented commands for use and handling in application
	private final List<ICommand> commands = new ArrayList<>();
	
	private String prefix;
	
	@Autowired
	private HelpCommand help;
	@Autowired
	private RollCharacter4d6DropLowestCommand roll4d6Drop1;
	@Autowired
	private RollCharacter4d6Reroll1DropLowestCommand roll4d6Reroll1Drop1;
	
	public CommandManager(@Value("${discord.bot.prefix.default}") String prefix) {
		this.prefix = prefix;
	}
	
	@PostConstruct
	private void setUp() {
		help.setManager(this);
		addCommand(help);
		addCommand(roll4d6Drop1);
		addCommand(roll4d6Reroll1Drop1);
	}

	// Method to add a command to memory
	private void addCommand(ICommand cmd) {
		// Checks to see if command all ready exists in memory.
		boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));
		// If command exists then throws an error.
		if (nameFound) {
			throw new IllegalArgumentException("A Command with this name is allready present");
		}
		// Adds command to memory
		commands.add(cmd);
	}

	// Gets a list of all commands in memory.
	public List<ICommand> getCommands() {
		return commands;
	}

	// Method searches for command in memory and returns the context of the command if found otherwise will return null.
	@Nullable
	public ICommand getCommand(String search) {
		String searchLower = search.toLowerCase();
		for (ICommand cmd : this.commands) {
			if (cmd.getName().toLowerCase().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
				return cmd;
			}
		}
		return null;
	}

	// Method to handle commands.
	void handle(GuildMessageReceivedEvent event) {
		// Gets command message and cleans it up.
		String[] split = event.getMessage().getContentRaw()
				.replaceFirst("(?i)" + Pattern.quote(prefix), "").split("\\s+");
		String invoke = split[0].toLowerCase();
		// Creates the command context using the getCommand method. Null if no command found
		ICommand cmd = this.getCommand(invoke);
		// Checks if command is not null before sending command to the command context handler.
		if (cmd != null) {
			event.getChannel().sendTyping().queue();
			List<String> args = Arrays.asList(split).subList(1, split.length);
			CommandContext ctx = new CommandContext(event, args);
			cmd.handle(ctx);
		}
	}
}
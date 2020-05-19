package com.mellora.rpgbot.service.command.commands;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mellora.rpgbot.service.CommandManager;
import com.mellora.rpgbot.service.command.CommandContext;
import com.mellora.rpgbot.service.command.ICommand;

import net.dv8tion.jda.api.entities.TextChannel;

@Service
public class HelpCommand implements ICommand {

	private final CommandManager manager;
	private final String prefix;
	
	public HelpCommand(CommandManager manager, String prefix) {
		this.manager = manager;
		this.prefix = prefix;
	}

	// Method handles the command logic.
	@Override
	public void handle(CommandContext ctx) {

		// Gets needed values from Command Context.
		List<String> args = ctx.getArgs();
		TextChannel channel = ctx.getChannel();

		// Checks if args is empty and outputs all command names to guild if empty.
		if (args.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			builder.append("List of commands\n");
			manager.getCommands().stream().map(ICommand::getName)
					.forEach((it) -> builder.append("`").append(prefix).append(it).append("`\n"));
			channel.sendMessage(builder.toString()).queue();
			return;
		}

		// Gets command to search for from args.
		String search = args.get(0);
		ICommand command = manager.getCommand(search);

		// Sends that command doesn't exist if search returns null value.
		if (command == null) {
			channel.sendMessage("Nothing found for " + search).queue();
			return;
		}

		// Sends the help context for searched for command.
		channel.sendMessage(command.getHelp()).queue();
	}

	// Method returns the command name for Discord.
	@Override
	public String getName() {
		return "help";
	}

	// Method returns the commands help context.
	@Override
	public String getHelp() {
		return "Shows the list with commands in the bot\nUsage: " + prefix + this.getName()
				+ " [command]";
	}

	// Method returns all other ways to refer to this command.
	@Override
	public List<String> getAliases() {
		return List.of("commands", "cmds", "commandlist");
	}

}

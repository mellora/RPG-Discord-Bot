package com.mellora.rpgbot.bot.command;

import java.util.List;

import me.duncte123.botcommons.commands.ICommandContext;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/*
 * Class that handles the information on every fired event that is 
 * classified as a command.  This information is then passed to the
 * command class.
 */
public class CommandContext implements ICommandContext {

	private final GuildMessageReceivedEvent event;
	private final List<String> args;

	public CommandContext(GuildMessageReceivedEvent event, List<String> args) {
		this.event = event;
		this.args = args;
	}

	// Method Returns the guild that fired the event to be handled
	@Override
	public Guild getGuild() {
		return this.getEvent().getGuild();
	}

	// Method Returns the event that fired.
	@Override
	public GuildMessageReceivedEvent getEvent() {
		return this.event;
	}

	// Method Returns the command arguments from the event.
	public List<String> getArgs() {
		return this.args;
	}

}

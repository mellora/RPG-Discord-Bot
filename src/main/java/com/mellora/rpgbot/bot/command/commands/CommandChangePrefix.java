package com.mellora.rpgbot.bot.command.commands;

import com.mellora.rpgbot.bot.command.CommandContext;
import com.mellora.rpgbot.bot.command.ICommand;
import com.mellora.rpgbot.dao.GuildSettingsRepo;

public class CommandChangePrefix implements ICommand{

	private final GuildSettingsRepo repo;
	
	public CommandChangePrefix(GuildSettingsRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public void handle(CommandContext ctx) {

	}

	@Override
	public String getName() {
		return "Prefix";
	}

	@Override
	public String getHelp() {
		return null;
	}

}

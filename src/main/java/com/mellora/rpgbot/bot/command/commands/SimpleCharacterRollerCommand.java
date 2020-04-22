package com.mellora.rpgbot.bot.command.commands;

import com.mellora.rpgbot.Config;
import com.mellora.rpgbot.bot.command.CommandContext;
import com.mellora.rpgbot.bot.command.ICommand;
import com.mellora.rpgbot.bot.service.DiceRoller;

public class SimpleCharacterRollerCommand implements ICommand{

	DiceRoller roller;
	
	@Override
	public void handle(CommandContext ctx) {
		roller = new DiceRoller();
		ctx.getChannel().sendMessageFormat("Score 1: %d", roller.rollD4()).queue();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "simple-c";
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "Rolls  up 6 dice scores of 4 4-sided dice each\nUsage: " + Config.get("default_prefix") + this.getName();
	}

}

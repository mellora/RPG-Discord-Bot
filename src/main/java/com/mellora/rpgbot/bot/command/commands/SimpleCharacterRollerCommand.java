package com.mellora.rpgbot.bot.command.commands;

import java.awt.Color;
import java.util.List;

import com.mellora.rpgbot.Config;
import com.mellora.rpgbot.bot.command.CommandContext;
import com.mellora.rpgbot.bot.command.ICommand;
import com.mellora.rpgbot.bot.service.CharacterRoller;

import net.dv8tion.jda.api.EmbedBuilder;

public class SimpleCharacterRollerCommand implements ICommand {

	CharacterRoller characterRoll;
	EmbedBuilder eb;

	@Override
	public void handle(CommandContext ctx) {
		characterRoll = new CharacterRoller();
		eb = new EmbedBuilder();

		List<Integer> rolls = characterRoll.simpleCharacter();

		eb.setTitle("Character Scores for " + ctx.getAuthor().getName());

		eb.setColor(Color.PINK);

		for (int x = 1; x <= rolls.size(); x++) {
			eb.addField("Score " + x + ": ", rolls.get(x - 1).toString(), true);
		}

		ctx.getChannel().sendMessage(eb.build()).queue();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "simple-c";
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "Rolls  up 6 dice scores of 4 4-sided dice each\nUsage: " + Config.get("default_prefix")
				+ this.getName();
	}

}

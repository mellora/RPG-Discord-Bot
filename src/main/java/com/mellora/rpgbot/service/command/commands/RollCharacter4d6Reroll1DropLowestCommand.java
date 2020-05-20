package com.mellora.rpgbot.service.command.commands;

import java.awt.Color;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mellora.rpgbot.service.command.CommandContext;
import com.mellora.rpgbot.service.command.ICommand;
import com.mellora.rpgbot.service.dice.CharacterRoller;

import net.dv8tion.jda.api.EmbedBuilder;

@Service
public class RollCharacter4d6Reroll1DropLowestCommand implements ICommand {

	@Autowired
	private CharacterRoller characterRoll;
	
	private EmbedBuilder eb;
	
	@Value("${discord.bot.prefix.default}")
	private String prefix;

	// Method handles the command logic.
	@Override
	public void handle(CommandContext ctx) {

		// Initializes a new EmbedBuilder for output.
		eb = new EmbedBuilder();

		// Initializes a list of all scores to be output.
		List<Integer> rolls = characterRoll.roll4d6DropLowestDieReroll1ForScores();

		// Sets the embed title.
		eb.setTitle("Character Scores for " + ctx.getAuthor().getName());

		// Sets the embed color.
		eb.setColor(Color.PINK);

		// Loops through all the rolls and add the roll score to an embed field and adds
		// it to the embed inline.
		for (int x = 1; x <= rolls.size(); x++) {
			eb.addField("Score " + x + ": ", rolls.get(x - 1).toString(), true);
		}

		// Sends embed to channel of the guild who used the command.
		ctx.getChannel().sendMessage(eb.build()).queue();
	}

	// Method returns the command name for Discord.
	@Override
	public String getName() {
		return "rollCharacter-4d6-advanced";
	}

	// Method returns the commands help context.
	@Override
	public String getHelp() {
		return "Rolls 6 dice scores of 4 6-sided dice rerolling 1's and dropping the lowest die role for each\nUsage: "
				+ prefix + this.getName();
	}

}

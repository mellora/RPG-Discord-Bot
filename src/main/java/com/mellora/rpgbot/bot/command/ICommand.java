package com.mellora.rpgbot.bot.command;

import java.util.List;

public interface ICommand {
	void handle(CommandContext ctx);
	
	String getName();
	
	String getHelp();
	
	default List<String> getAliases() {
		return List.of();  // Introduced in Java 9, Use Arrays.asList on Java 8
	}
}

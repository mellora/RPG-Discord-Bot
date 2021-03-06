package com.mellora.rpgbot.service.command;

import java.util.List;

//Interface to help control behavior of all commands created.
public interface ICommand {
	void handle(CommandContext ctx);
	
	String getName();
	
	String getHelp();
	
	default List<String> getAliases() {
		return List.of();  // Introduced in Java 9, Use Arrays.asList on Java 8
	}
}
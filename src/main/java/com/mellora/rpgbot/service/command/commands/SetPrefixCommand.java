package com.mellora.rpgbot.service.command.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mellora.rpgbot.dao.GuildSettingsRepository;
import com.mellora.rpgbot.dao.entities.GuildSettings;
import com.mellora.rpgbot.service.command.CommandContext;
import com.mellora.rpgbot.service.command.ICommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

@Service
public class SetPrefixCommand implements ICommand {

	@Autowired
	private GuildSettingsRepository repo;
	
	private String prefix;
	
	@Override
	public void handle(CommandContext ctx) {
		
		final TextChannel channel = ctx.getChannel();
		final List<String> args = ctx.getArgs();
		final Member member = ctx.getMember();

		final GuildSettings guild = repo.getGuildByGuildId(ctx.getGuild().getIdLong());
		
		if(!member.hasPermission(Permission.MANAGE_SERVER)) {
			channel.sendMessage("You do not have the power to manage this server.").queue();
			return;
		}
		
		if (args.isEmpty()) {
			channel.sendMessage("Missing Args").queue();
			return;
		}
		
		final String newPrefix = String.join("", args);
		
		guild.setPrefix(newPrefix);
		repo.save(guild);
		
		channel.sendMessageFormat("New prefix has been set to `%s`", newPrefix);

	}

	@Override
	public String getName() {
		return "setPrefix";
	}

	@Override
	public String getHelp() {
		return "Sets the prefix for this server.\nUsage: " + prefix + this.getName()
		+ " <prefix>";
	}

}

package com.mellora.rpgbot.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GuildSettings {
	
	private Integer id;
	private Long guild_id;
	private String prefix;
	
	public GuildSettings() {
		super();
	}
	
	public GuildSettings(Long guild_id) {
		this.guild_id = guild_id;
	}
	
	public GuildSettings(Long guild_id, String prefix) {
		this.guild_id = guild_id;
		this.prefix = prefix;
	}
	
	public GuildSettings(Integer id, Long guild_id, String prefix) {
		this.id = id;
		this.guild_id = guild_id;
		this.prefix = prefix;
	}
}

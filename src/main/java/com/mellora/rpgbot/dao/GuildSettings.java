package com.mellora.rpgbot.dao;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(value = "guild_settings")
public class GuildSettings {
	
	private Integer id;
	private Long guild_id;
	private String prefix;
	
	public GuildSettings() {
		super();
	}
	
	public GuildSettings(Integer id, Long guild_id, String prefix) {
		this.id = id;
		this.guild_id = guild_id;
		this.prefix = prefix;
	}
}

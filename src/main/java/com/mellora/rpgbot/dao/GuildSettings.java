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
	
}

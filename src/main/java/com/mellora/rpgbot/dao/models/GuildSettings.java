package com.mellora.rpgbot.dao.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GuildSettings {

	private Integer id;
	private Long guildId;
	private String prefix;

	public GuildSettings() {
		super();
	}
}

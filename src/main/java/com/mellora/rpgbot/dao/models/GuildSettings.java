package com.mellora.rpgbot.dao.models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GuildSettings {
	
	@Id
	private Integer id;
	private Long guildId;
	private String prefix;

	public GuildSettings() {
		super();
	}

	public GuildSettings(Long guildId) {
		this.guildId = guildId;
	}

	public GuildSettings(Long guildId, String prefix) {
		this.guildId = guildId;
		this.prefix = prefix;
	}

	public GuildSettings(Integer id, Long guildId, String prefix) {
		this.id = id;
		this.guildId = guildId;
		this.prefix = prefix;
	}
}

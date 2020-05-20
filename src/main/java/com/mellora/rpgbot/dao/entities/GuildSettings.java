package com.mellora.rpgbot.dao.entities;

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
}

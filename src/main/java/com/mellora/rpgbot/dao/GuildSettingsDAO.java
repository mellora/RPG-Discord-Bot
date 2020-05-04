package com.mellora.rpgbot.dao;

import java.util.List;

import com.mellora.rpgbot.dao.models.GuildSettings;

public interface GuildSettingsDAO {

	// Create
	public Integer save(Long guildId);

	// Read
	public GuildSettings getByGuildId(Long guildId);

	// Update
	public Integer update(GuildSettings guildSettings);

	// Delete
	public Integer deleteByGuildId(Long guildId);

	// Get All
	public List<GuildSettings> getAll();
}

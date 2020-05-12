package com.mellora.rpgbot.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mellora.rpgbot.dao.models.GuildSettings;

public interface GuildSettingsDAO {

	public JdbcTemplate getTemplate();
	
	// Create
	public Integer save(Long guildId);

	// Read
	public GuildSettings getByGuildId(Long guildId);
	public Boolean findIfGuildExists(Long guildId);

	// Update
	public Integer update(GuildSettings guildSettings);

	// Delete
	public Integer deleteByGuildId(Long guildId);

	// Get All
	public List<GuildSettings> getAll();
}

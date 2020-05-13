package com.mellora.rpgbot.dao;

import java.util.List;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mellora.rpgbot.Config;
import com.mellora.rpgbot.dao.models.GuildSettings;

import lombok.extern.slf4j.Slf4j;

/*
 * Static class to facilitate communications to the database.
 */
@Slf4j
@Repository
public class GuildSettingsRepo implements GuildSettingsDAO {

	private JdbcTemplate jdbcTemplate;
	
	public GuildSettingsRepo() {
		log.info("Database Initilized");
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(Config.get("driver_class"));
		dataSourceBuilder.url(Config.get("url"));
		dataSourceBuilder.username(Config.get("username"));
		dataSourceBuilder.password(Config.get("password"));
		jdbcTemplate = new JdbcTemplate(dataSourceBuilder.build());
	}

	// Returns the template for special queries.
	@Override
	public JdbcTemplate getTemplate() {
		log.info("Repo getTemplate Called");
		return jdbcTemplate;
	}
	
	// Inserts guild into database.
	@Override
	public Integer save(Long guildId) {
		log.info("Repo save called");
		String sql = "INSERT INTO guild_settings (guild_id, prefix) VALUES ( ? , ?)";
		return jdbcTemplate.update(sql, guildId, Config.get("default_prefix"));
	}

	// Gets the information for a guild from the database.
	@Override
	@Cacheable(cacheNames = "guildSettings", key = "#guildId")
	public GuildSettings getByGuildId(Long guildId) {
		log.info("Repo getGuildById called");
		String sql = "SELECT * FROM guild_settings WHERE guild_id = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { guildId },
				new BeanPropertyRowMapper<>(GuildSettings.class));
	}

	// Checks if Guild exists in the database.
	@Override
	public Boolean findIfGuildExists(Long guildId) {
		log.info("Repo findIfGuildExists called");
		String sql = "SELECT COUNT(*) FROM guild_settings WHERE guild_id = ?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] { guildId }, Integer.class);
		return count > 0;
	}

	// Updates the guild settings.
	@Override
	@CachePut(cacheNames = "guildSettings", key = "#guildSettings.id")
	public Integer update(GuildSettings guildSettings) {
		log.info("Repo update called");
		return 0;
	}

	// Deletes a guild from the database.
	@Override
	@CacheEvict(cacheNames = "guildSettings", key = "#guildId")
	public Integer deleteByGuildId(Long guildId) {
		log.info("Repo deleteByGuildId called");
		String sql = "DELETE FROM guild_settings WHERE guild_id = ?";
		return jdbcTemplate.update(sql, guildId);
	}

	// Returns all guilds from the database.
	@Override
	public List<GuildSettings> getAll() {
		log.info("Repo getAll called");
		String sql = "SELECT * FROM guild_settings";
		return jdbcTemplate.query(sql, (rs, rowNum) ->new GuildSettings(rs.getInt("id"), rs.getLong("guild_id"), rs.getString("prefix")));
	}
}

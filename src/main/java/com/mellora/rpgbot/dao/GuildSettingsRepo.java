package com.mellora.rpgbot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mellora.rpgbot.Config;
import com.mellora.rpgbot.dao.models.GuildSettings;

/*
 * Static class to facilitate communications to the database.
 */
@Repository
public class GuildSettingsRepo implements GuildSettingsDAO {

	// Spring Boot will create and configure DataSource and JdbcTemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getTemplate() {
		return jdbcTemplate;
	}
	
	// Inserts guild into database.
	@Override
	public Integer save(Long guildId) {
		String sql = "INSERT INTO guild_settings (guild_id, prefix) VALUES ( ? , ?)";
		return jdbcTemplate.update(sql, guildId, Config.get("default_prefix"));
	}

	// Gets the information for a guild from the database.
	@Override
	public GuildSettings getByGuildId(Long guildId) {
		String sql = "SELECT * FROM guild_settings WHERE guild_id = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { guildId },
				new BeanPropertyRowMapper<>(GuildSettings.class));
	}

	@Override
	public Boolean findIfGuildExists(Long guildId) {
		String sql = "SELECT COUNT(*) FROM guild_settings WHERE guild_id = ?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] { guildId }, Integer.class);
		return count > 0;
	}

	@Override
	public Integer update(GuildSettings guildSettings) {
		return 0;
	}

	@Override
	public Integer deleteByGuildId(Long guildId) {
		String sql = "DELETE FROM guild_settings WHERE guild_id = ?";
		return jdbcTemplate.update(sql, guildId);
	}

	@Override
	public List<GuildSettings> getAll() {
		String sql = "SELECT * FROM guild_settings";
		return jdbcTemplate.query(sql, (rs, rowNum) ->new GuildSettings(rs.getInt("id"), rs.getLong("guild_id"), rs.getString("prefix")));
	}
}

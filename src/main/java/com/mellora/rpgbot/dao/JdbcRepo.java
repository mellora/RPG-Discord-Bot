package com.mellora.rpgbot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*
 * Static class to facilitate communications to the database.
 */
@Repository
public class JdbcRepo {

	// Gets the pre-set methods from Spring Data JDBC.
	@Autowired
	public static JdbcTemplate jdbcTemplate;
	
	// Gets the information for a guild from the database.
	public static GuildSettings findByGuildId(long guild_id) {
		String sql = "SELECT * FROM guild_settings WHERE guild_id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{guild_id}, new BeanPropertyRowMapper<>(GuildSettings.class));
	}
	
	// Inserts guild into database.
	public static int addGuild(long guild_id) {
		String sql = "INSERT INTO guild_settings (guild_id) VALUES (?)";
		return jdbcTemplate.update(sql, guild_id);
	}
}

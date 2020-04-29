package com.mellora.rpgbot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class JdbcRepo {

	@Autowired
	private static JdbcTemplate jdbcTemplate;

	public JdbcRepo() {
		
	}
	
	public static GuildSettings findByGuildId(long guild_id) {
		String sql = "SELECT * FROM guild_settings WHERE guild_id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{guild_id}, new BeanPropertyRowMapper<>(GuildSettings.class));
	}
}

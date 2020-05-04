package com.mellora.rpgbot.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mellora.rpgbot.dao.models.GuildSettings;

/*
 * Static class to facilitate communications to the database.
 */
@Repository
public class GuildSettingsRepo  implements GuildSettingsDAO{

	private DataSource dataSource;
	
	// Gets the pre-set methods from Spring Data JDBC.
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// Inserts guild into database.
	@Override
	public Integer save(Long guildId) {
		String sql = "INSERT INTO guild_settings (guild_id) VALUES (?)";
		return jdbcTemplate.update(sql, guildId);
	}

	// Gets the information for a guild from the database.
	@Override
	public GuildSettings getByGuildId(Long guildId) {
		String sql = "SELECT * FROM guild_settings WHERE guild_id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{guildId}, new BeanPropertyRowMapper<>(GuildSettings.class));
	}

	@Override
	public Integer update(GuildSettings guildSettings) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer deleteByGuildId(Long guildId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GuildSettings> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean findOutIfRecordExists(Long guildId) {
		String sql = "SELECT COUNT(*) FROM guild_settings WHERE guild_id = ?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] {guildId}, Integer.class);
		return count > 0;
	}
}

package com.mellora.rpgbot.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mellora.rpgbot.dao.entities.GuildSettings;

@Repository
public interface GuildSettingsRepository extends CrudRepository<GuildSettings, Integer>{

	@Query(value = "SELECT * FROM guild_settings WHERE guild_id = :guild_id")
	GuildSettings getGuildByGuildId(@Param(value = "guild_id") Long guildId);
	
	@Query(value = "UPDATE guild_settings SET prefix = :prefix WHERE guild_id = :guild_id")
	Integer updatePrefix(@Param(value = "prefix") String prefix, @Param(value = "guild_id") Long guildId);
}

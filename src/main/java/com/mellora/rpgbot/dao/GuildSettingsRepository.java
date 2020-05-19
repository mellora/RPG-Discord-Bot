package com.mellora.rpgbot.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GuildSettingsRepository extends CrudRepository<GuildSettings, Integer>{

	@Query(value = "SELECT * FROM guild_setting WHERE guild_id = :guild_id")
	GuildSettings getGuildByGuildId(@Param(value = "guild_id") Long guildId);
}

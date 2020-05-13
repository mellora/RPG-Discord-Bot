package com.mellora.rpgbot.dao;

import org.springframework.data.repository.CrudRepository;

import com.mellora.rpgbot.dao.models.GuildSettings;

public interface GuildSettingsRepository extends CrudRepository<GuildSettings, Long>{

}

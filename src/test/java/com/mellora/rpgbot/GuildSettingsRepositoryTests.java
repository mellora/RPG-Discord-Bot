package com.mellora.rpgbot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import com.mellora.rpgbot.dao.GuildSettingsRepository;
import com.mellora.rpgbot.dao.entities.GuildSettings;

@DataJdbcTest
class GuildSettingsRepositoryTests {
	
	@Autowired
	GuildSettingsRepository repo;
	
	@Test
	void addNewGuild() {
		final GuildSettings save = repo.save(new GuildSettings(null, (long) 1234567890, "!!"));
		assertThat(save).isNotNull();
	}

}

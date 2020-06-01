package com.mellora.rpgbot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import com.mellora.rpgbot.dao.GuildSettingsRepository;
import com.mellora.rpgbot.dao.entities.GuildSettings;

@DataJdbcTest
class GuildSettingsRepositoryTests {

	@Autowired
	GuildSettingsRepository repo;

	@BeforeEach
	// Adds junk data to h2 repo for testing.
	void setUp() {
		repo.save(new GuildSettings(null, (long) 111111111, "!!"));
		repo.save(new GuildSettings(null, (long) 222222222, "$$"));
		repo.save(new GuildSettings(null, (long) 333333333, "@@"));
		repo.save(new GuildSettings(null, (long) 444444444, "#"));
		repo.save(new GuildSettings(null, (long) 555555555, "&"));
	}

	@Test
	// Test the save functionality.
	void addNewGuild() {
		GuildSettings save = repo.save(new GuildSettings(null, (long) 1234567890, "!!"));
		assertThat(save).isNotNull();
	}

	@Test
	// Tests the guild retrieval functionality.
	void getGuild() {
		GuildSettings save1 = repo.getGuildByGuildId((long) 111111111);
		GuildSettings save2 = repo.getGuildByGuildId((long) 222222222);
		GuildSettings save3 = repo.getGuildByGuildId((long) 333333333);
		GuildSettings save4 = repo.getGuildByGuildId((long) 444444444);
		GuildSettings save5 = repo.getGuildByGuildId((long) 555555555);

		assertThat(save1).isNotNull();
		assertThat(save1.getPrefix()).isEqualTo("!!");

		assertThat(save2).isNotNull();
		assertThat(save2.getPrefix()).isEqualTo("$$");

		assertThat(save3).isNotNull();
		assertThat(save3.getPrefix()).isEqualTo("@@");

		assertThat(save4).isNotNull();
		assertThat(save4.getPrefix()).isEqualTo("#");

		assertThat(save5).isNotNull();
		assertThat(save5.getPrefix()).isEqualTo("&");
	}

	@Test
	// Tests the guild update functionality.
	void updateGuild() {
		// Get preliminary objects
		GuildSettings save1 = repo.getGuildByGuildId((long) 111111111);
		GuildSettings save2 = repo.getGuildByGuildId((long) 222222222);
		GuildSettings save3 = repo.getGuildByGuildId((long) 333333333);
		GuildSettings save4 = repo.getGuildByGuildId((long) 444444444);
		GuildSettings save5 = repo.getGuildByGuildId((long) 555555555);
		// Update objects
//		int save1int = repo.updatePrefix("!", (long) 111111111);
//		int save2int = repo.updatePrefix("@", (long) 222222222);
//		int save3int = repo.updatePrefix("#", (long) 333333333);
//		int save4int = repo.updatePrefix("$", (long) 444444444);
//		int save5int = repo.updatePrefix("%", (long) 555555555);
		save1.setPrefix("!");
		GuildSettings save1update = repo.save(save1);
		save2.setPrefix("@");
		GuildSettings save2update = repo.save(save2);
		save3.setPrefix("#");
		GuildSettings save3update = repo.save(save3);
		save4.setPrefix("$");
		GuildSettings save4update = repo.save(save4);
		save5.setPrefix("%");
		GuildSettings save5update = repo.save(save5);
		// Get updated objects
//		GuildSettings save1update = repo.getGuildByGuildId((long) 111111111);
//		GuildSettings save2update = repo.getGuildByGuildId((long) 222222222);
//		GuildSettings save3update = repo.getGuildByGuildId((long) 333333333);
//		GuildSettings save4update = repo.getGuildByGuildId((long) 444444444);
//		GuildSettings save5update = repo.getGuildByGuildId((long) 555555555);
		GuildSettings save1updateFromRepo = repo.getGuildByGuildId((long) 111111111);
		GuildSettings save2updateFromRepo = repo.getGuildByGuildId((long) 222222222);
		GuildSettings save3updateFromRepo = repo.getGuildByGuildId((long) 333333333);
		GuildSettings save4updateFromRepo = repo.getGuildByGuildId((long) 444444444);
		GuildSettings save5updateFromRepo = repo.getGuildByGuildId((long) 555555555);
		// Check if updates went through.
		assertThat(save1update).isNotNull();
		assertThat(save2update).isNotNull();
		assertThat(save3update).isNotNull();
		assertThat(save4update).isNotNull();
		assertThat(save5update).isNotNull();
		// Check if repository was modified.
		assertThat(save1updateFromRepo).isNotEqualTo(save1);
		assertThat(save2updateFromRepo).isNotEqualTo(save2);
		assertThat(save3updateFromRepo).isNotEqualTo(save3);
		assertThat(save4updateFromRepo).isNotEqualTo(save4);
		assertThat(save5updateFromRepo).isNotEqualTo(save5);
	}

}

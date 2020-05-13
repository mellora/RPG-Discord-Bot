package com.mellora.rpgbot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mellora.rpgbot.dao.GuildSettingsRepository;
import com.mellora.rpgbot.dao.models.GuildSettings;

/*
 * Testing For Database
 */
@SpringBootTest
@DataJdbcTest // Allows tests to run for a database
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CustomRepositoryTests {
 
	@Autowired
	GuildSettingsRepository repo;
	
	@Test
	public void test() {
		GuildSettings settings = new GuildSettings((long)32423423);
		GuildSettings save = repo.save(settings);
		assertThat(save).isNotNull();
	}
}

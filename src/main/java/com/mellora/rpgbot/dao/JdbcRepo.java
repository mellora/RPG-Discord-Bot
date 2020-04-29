package com.mellora.rpgbot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Repository
public class JdbcRepo {

	@Autowired
	JdbcTemplate jdbcTemplate;

}

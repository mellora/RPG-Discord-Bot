package com.mellora.rpgbot.bot.service;

import java.util.Random;

public class DiceRoller {

	Random random = new Random();

	public DiceRoller() {

	}

	public int rollD4() {
		return random.nextInt(4) + 1;
	}

	public int rollD6() {
		return random.nextInt(6) + 1;
	}

	public int rollD8() {
		return random.nextInt(8) + 1;
	}

	public int rollD10() {
		return random.nextInt(10) + 1;
	}

	public int rollD12() {
		return random.nextInt(12) + 1;
	}

	public int rollD20() {
		return random.nextInt(20) + 1;
	}

	public int rollD100() {
		return random.nextInt(100) + 1;
	}
}

package com.mellora.rpgbot.bot.service;

import java.util.Random;

public class DiceRoller {
	Random random = new Random();

	public DiceRoller() {

	}

	public int rollD4() {
		return random.nextInt(4);
	}

	public int rollD6() {
		return random.nextInt(6);
	}

	public int rollD8() {
		return random.nextInt(8);
	}

	public int rollD10() {
		return random.nextInt(10);
	}

	public int rollD12() {
		return random.nextInt(12);
	}

	public int rollD20() {
		return random.nextInt(20);
	}

	public int rollD100() {
		return random.nextInt(100);
	}
}

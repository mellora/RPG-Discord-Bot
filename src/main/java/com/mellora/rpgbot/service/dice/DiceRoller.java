package com.mellora.rpgbot.service.dice;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class DiceRoller {

	// Creates a private random instance
	private Random random;
	
	DiceRoller(){
		random = new Random();
	}

	// Method sets the random instance seed based on system time at method execution.
	public void newRandomSeed() {
		random.setSeed(System.currentTimeMillis());
	}
	
	public void setRandomSeed(Long seed) {
		random.setSeed(seed);
	}

	/*
	 * Following methods simulate the dice found in the typical RPG dice set.
	 * Dice are listed as follows:
	 * D4, D6, D8, D10, D12, D20, and D100.
	 */
	
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
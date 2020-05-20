package com.mellora.rpgbot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import com.mellora.rpgbot.service.dice.DiceRoller;
import com.mellora.rpgbot.service.dice.MultiDiceRoller;

class MultiDiceRollerTests {

	private static final int numberOfTests = 5;
	
	private static MultiDiceRoller roller;
	
	@BeforeAll
	static void setUp() {
		roller = new MultiDiceRoller(new DiceRoller());
	}
	
	@RepeatedTest(value = numberOfTests)
	void testRollD4() {
		List<Integer> rolls = roller.rollD4(4);
		assertThat(rolls.size() == 4);
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD6() {
		List<Integer> rolls = roller.rollD6(4);
		assertThat(rolls.size() == 4);
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD8() {
		List<Integer> rolls = roller.rollD8(4);
		assertThat(rolls.size() == 4);
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD10() {
		List<Integer> rolls = roller.rollD10(4);
		assertThat(rolls.size() == 4);
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD12() {
		List<Integer> rolls = roller.rollD12(4);
		assertThat(rolls.size() == 4);
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD20() {
		List<Integer> rolls = roller.rollD20(4);
		assertThat(rolls.size() == 4);
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD100() {
		List<Integer> rolls = roller.rollD100(4);
		assertThat(rolls.size() == 4);
	}

}

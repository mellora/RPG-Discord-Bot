package com.mellora.rpgbot;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.RepeatedTest;

import com.mellora.rpgbot.service.dice.DiceRoller;

class DiceRollerTests {

	private static final int numberOfTests = 5;
	
	private static DiceRoller roller = new DiceRoller();
	
	@RepeatedTest(value = numberOfTests)
	void testRollD4() {
		int roll = roller.rollD4();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(4)));
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD6() {
		int roll = roller.rollD6();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(6)));
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD8() {
		int roll = roller.rollD8();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(8)));
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD10() {
		int roll = roller.rollD10();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(10)));
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD12() {
		int roll = roller.rollD12();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD20() {
		int roll = roller.rollD20();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(20)));
	}

	@RepeatedTest(value = numberOfTests)
	void testRollD100() {
		int roll = roller.rollD100();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(100)));
	}

}

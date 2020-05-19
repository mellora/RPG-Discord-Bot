package com.mellora.rpgbot;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.mellora.rpgbot.service.dice.DiceRoller;

class DiceRollerTests {

	private static DiceRoller roller = mock(DiceRoller.class);

	@Test
	void testRollD4() {
		int roll = roller.rollD4();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(4)));
	}

	@Test
	void testRollD6() {
		int roll = roller.rollD6();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(6)));
	}

	@Test
	void testRollD8() {
		int roll = roller.rollD8();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(8)));
	}

	@Test
	void testRollD10() {
		int roll = roller.rollD10();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(10)));
	}

	@Test
	void testRollD12() {
		int roll = roller.rollD12();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(12)));
	}

	@Test
	void testRollD20() {
		int roll = roller.rollD20();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(20)));
	}

	@Test
	void testRollD100() {
		int roll = roller.rollD100();
		assertThat(roll, allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(100)));
	}

}

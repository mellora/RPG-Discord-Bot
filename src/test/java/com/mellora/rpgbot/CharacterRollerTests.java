package com.mellora.rpgbot;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import com.mellora.rpgbot.service.dice.CharacterRoller;
import com.mellora.rpgbot.service.dice.DiceRoller;
import com.mellora.rpgbot.service.dice.MultiDiceRoller;

class CharacterRollerTests {

	private static final int numberOfTests = 10;

	private static CharacterRoller roller;

	@BeforeAll
	static void setUp() {
		roller = new CharacterRoller(new MultiDiceRoller(new DiceRoller()));
	}

	@RepeatedTest(value = numberOfTests)
	void testRoll4d6DropLowestDieForScores() {
		List<Integer> stats = roller.roll4d6DropLowestDieForScores();
		for (Integer stat : stats) {
			assertThat(stat, allOf(greaterThanOrEqualTo(3), lessThanOrEqualTo(18)));
		}
	}

	@RepeatedTest(value = numberOfTests)
	void testRoll4d6DropLowestDieReroll1ForScores() {
		List<Integer> stats = roller.roll4d6DropLowestDieForScores();
		for (Integer stat : stats) {
			assertThat(stat, allOf(greaterThanOrEqualTo(6), lessThanOrEqualTo(18)));
		}

	}

}

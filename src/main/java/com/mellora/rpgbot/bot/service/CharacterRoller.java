package com.mellora.rpgbot.bot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterRoller {

	private MultiDiceRoller multiDiceRoller;

	public CharacterRoller() {
		multiDiceRoller = new MultiDiceRoller();
	}

	public List<Integer> roll4d6DropLowestDieForScores() {
		multiDiceRoller.roller.newRandomSeed();
		List<Integer> list = new ArrayList<>();
		for (int x = 0; x < 6; x++) {
			List<Integer> temp = multiDiceRoller.rollD6(4);
			Collections.sort(temp);
			temp.remove(0);
			list.add(temp.stream().mapToInt(Integer::intValue).sum());
		}
		return list;
	}

	public List<Integer> roll4d6DropLowestDieReroll1ForScores() {
		multiDiceRoller.roller.newRandomSeed();
		List<Integer> list = new ArrayList<>();
		for (int x = 0; x < 6; x++) {
			List<Integer> temp = multiDiceRoller.rollD6(4);
			while (temp.contains(1)) {
				Collections.replaceAll(temp, 1, multiDiceRoller.roller.rollD6());
			}
			Collections.sort(temp);
			temp.remove(0);
			list.add(temp.stream().mapToInt(Integer::intValue).sum());
		}
		return list;
	}
}

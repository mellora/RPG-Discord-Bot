package com.mellora.rpgbot.bot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterRoller {

	MultiDiceRoller multiDiceRoller;
	DiceRoller dieRoller;

	public CharacterRoller() {
		multiDiceRoller = new MultiDiceRoller();
		dieRoller = new DiceRoller();
	}

	public List<Integer> simpleCharacter() {
		List<Integer> list = new ArrayList<>();
		for(int x = 0; x < 6; x++) {
			List<Integer> temp = multiDiceRoller.rollD6(4);
			Collections.sort(temp);
			temp.remove(0);
			list.add(temp.stream().mapToInt(Integer::intValue).sum());
		}
		return list;
	}
}

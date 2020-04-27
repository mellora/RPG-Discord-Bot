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
			System.out.println("List " + x + ": " + temp);
			while (temp.contains(1)) {
				int numberOfOnes = Collections.frequency(list, 1);
				System.out.println(numberOfOnes);
				List<Integer> newTemp = multiDiceRoller.rollD6(numberOfOnes);
				System.out.println(newTemp);
				temp.removeAll(Collections.singleton(1));
				temp.addAll(newTemp);
//				Collections.replaceAll(temp, 1, multiDiceRoller.roller.rollD6());
			}
			System.out.println("List " + x + ": " + temp);
			Collections.sort(temp);
			temp.remove(0);
			list.add(temp.stream().mapToInt(Integer::intValue).sum());
			System.out.println("");
		}
		return list;
	}
}

package com.mellora.rpgbot.bot.service.dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Class simulates popular ways to roll up character scores in D&D 5E
 */
public class CharacterRoller {

	// Instances a private MultiDiceRoller.
	private MultiDiceRoller multiDiceRoller;

	public CharacterRoller() {
		multiDiceRoller = new MultiDiceRoller();
	}

	/*
	 *  Simulates rolling 4 D6 and then removing the lowest roll.
	 *  This is the rules as written way.
	 *  Score Range: 3 - 18
	 */
	public List<Integer> roll4d6DropLowestDieForScores() {
		multiDiceRoller.roller.newRandomSeed();
		// Creates the master list that is returned.
		List<Integer> list = new ArrayList<>();
		// Simulates the rolling of the 6 scores to be used in character creation.
		for (int x = 0; x < 6; x++) {
			// Temp list creation for dice group calculation for total score.
			List<Integer> temp = multiDiceRoller.rollD6(4);
			// Sorts temp list in ascending order for die removal.
			Collections.sort(temp);
			// Removes the first object in list to simulate dropping the lowest score.
			temp.remove(0);
			// Uses stream to get the sum of the temp list and add that total to master list.
			list.add(temp.stream().mapToInt(Integer::intValue).sum());
		}
		return list;
	}
	/*
	 *  Simulates rolling 4 D6, re-rolling all rolled 1's, and then removing the lowest roll.
	 *  This is a slight modification to the rules as written way.
	 *  Score Range: 6 - 18
	 */
	public List<Integer> roll4d6DropLowestDieReroll1ForScores() {
		multiDiceRoller.roller.newRandomSeed();
		// Creates the master list that is returned.
		List<Integer> list = new ArrayList<>();
		// Simulates the rolling of the 6 scores to be used in character creation.
		for (int x = 0; x < 6; x++) {
			// Temp list creation for dice group calculation for total score.
			List<Integer> temp = multiDiceRoller.rollD6(4);
			// While loop that will run only if the temp list contains at least one instance of 1.
			while (temp.contains(1)) {
				// Creates a counter for figuring out how many 1's the temp list contains.
				int count = 0;
				// Loops through temp array and increments the counter if a 1 is found.
				for (int num : temp) {
					if (num == 1) {
						count++;
					}
				}
				// Simulates rerolling an amount of dice based on the counter's count and creates another temp list.
				List<Integer> newTemp = multiDiceRoller.rollD6(count);
				// Removes all 1's from the first temp list.
				temp.removeAll(Collections.singleton(1));
				// Adds the elements from the second temp list to the original temp list.
				temp.addAll(newTemp);
			}
			// Sorts temp list in ascending order for die removal.
			Collections.sort(temp);
			// Removes the first object in list to simulate dropping the lowest score.
			temp.remove(0);
			// Uses stream to get the sum of the temp list and add that total to master list.
			list.add(temp.stream().mapToInt(Integer::intValue).sum());
		}
		return list;
	}
}

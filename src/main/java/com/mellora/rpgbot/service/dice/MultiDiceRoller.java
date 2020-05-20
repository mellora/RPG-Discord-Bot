package com.mellora.rpgbot.service.dice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Class simulates rolling a number of dice based on input
 */
@Service
public class MultiDiceRoller {
	
	// Creates instance of DiceRoller class to be used within the Dice package
	DiceRoller roller;
	
	@Autowired
	public MultiDiceRoller(DiceRoller roller) {
		this.roller = roller;
	}
	
	/*
	 * Following Methods are for simulating rolling multiple dice grouped by die types.
	 * Dice Types are the based on the RPG dice sets and are as follows:
	 * D4, D6, D8, D10, D12, D20, and D100.
	 */
	
	public List<Integer> rollD4(int numberOfDice) {
		List<Integer> diceList = new ArrayList<>();
		for (int x = 0; x < numberOfDice; x++) {
			diceList.add(roller.rollD4());
		}
		return diceList;
	}
	
	public List<Integer> rollD6(int numberOfDice) {
		List<Integer> diceList = new ArrayList<>();
		for (int x = 0; x < numberOfDice; x++) {
			diceList.add(roller.rollD6());
		}
		return diceList;
	}
	
	public List<Integer> rollD8(int numberOfDice) {
		List<Integer> diceList = new ArrayList<>();
		for (int x = 0; x < numberOfDice; x++) {
			diceList.add(roller.rollD8());
		}
		return diceList;
	}
	
	public List<Integer> rollD10(int numberOfDice) {
		List<Integer> diceList = new ArrayList<>();
		for (int x = 0; x < numberOfDice; x++) {
			diceList.add(roller.rollD10());
		}
		return diceList;
	}
	
	public List<Integer> rollD12(int numberOfDice) {
		List<Integer> diceList = new ArrayList<>();
		for (int x = 0; x < numberOfDice; x++) {
			diceList.add(roller.rollD12());
		}
		return diceList;
	}
	
	public List<Integer> rollD20(int numberOfDice) {
		List<Integer> diceList = new ArrayList<>();
		for (int x = 0; x < numberOfDice; x++) {
			diceList.add(roller.rollD20());
		}
		return diceList;
	}
	
	public List<Integer> rollD100(int numberOfDice) {
		List<Integer> diceList = new ArrayList<>();
		for (int x = 0; x < numberOfDice; x++) {
			diceList.add(roller.rollD100());
		}
		return diceList;
	}
}

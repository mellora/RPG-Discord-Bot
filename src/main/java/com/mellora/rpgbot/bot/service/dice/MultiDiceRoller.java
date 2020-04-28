package com.mellora.rpgbot.bot.service.dice;

import java.util.ArrayList;
import java.util.List;

public class MultiDiceRoller {
	
	DiceRoller roller;
	
	public MultiDiceRoller(){
		roller = new DiceRoller();
	}
	
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

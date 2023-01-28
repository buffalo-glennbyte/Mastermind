package mastermind.game;

import java.util.Scanner;

import mastermind.io.IO;

import java.util.Random;

public class Game {
	final char[] kleuren = {'a','b','c','d','e','f'};
	private boolean gameLoop = true;
	
	public void start() {
		while (gameLoop) {
			System.out.println("Geef invoer, q om te stoppen:");
			System.out.println(new IO().UserInput() + "\n");
		}
	}
}
